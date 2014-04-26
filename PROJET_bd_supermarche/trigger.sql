
---mettre les produits dans le magasin----------------------------------------------------------------------
CREATE OR REPLACE FUNCTION archive_produit() RETURNS TRIGGER AS $$
DECLARE
	c produit_magasin.prix_vente%TYPE;
BEGIN
	SELECT prix_vente FROM produit a,produit_magasin b INTO c
	WHERE a.id_produit=NEW.id_produit AND a.id_produit=b.id_produit AND a.reference_local=0 AND b.prix_vente!=NEW.prix_vente;
	IF FOUND THEN----REFERENCE_LOCAL=0 MAIS PRIX DANS DIFFERENT MAGZINE PAS LE MEME			
		RAISE NOTICE 'REFRENCE_LOCAL=0 LE PRIX DE PRODUIT ID:% EST TOUJOURS %',NEW.id_produit,c;	
		RETURN NULL;	
	ELSE 
		RETURN NEW;
	END IF;	
END;
$$ LANGUAGE 'PLPGSQL';

DROP TRIGGER IF EXISTS archive_produit ON produit_magasin;
CREATE TRIGGER  archive_produit 
BEFORE INSERT ON produit_magasin
FOR EACH ROW 
EXECUTE PROCEDURE archive_produit();


------jetter PRODUIT ---------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION supr_produits_perimes() RETURNS TRIGGER AS $$
DECLARE
	a RECORD;
BEGIN
        FOR a IN
	SELECT * FROM produit WHERE NEW.dates > produit.date_peremption
	LOOP
		UPDATE produit_magasin SET quantite_stock = 0 WHERE id_produit=a.id_produit;
		
		
	END LOOP;
	
	RETURN NULL;
END;
$$ LANGUAGE 'plpgsql';

DROP TRIGGER IF EXISTS supr_produits_perimes ON Dates;
CREATE TRIGGER supr_produits_perimes 
AFTER UPDATE ON Dates
FOR EACH ROW
EXECUTE PROCEDURE supr_produits_perimes();

---mettre les produits dans le cataloge-------------------------------------------------------------
CREATE OR REPLACE FUNCTION met_produit_cataloge() RETURNS TRIGGER AS $$
DECLARE
	c cataloge.date_debut%TYPE;
	d cataloge.date_fin%TYPE;
	e cataloge.date_debut%TYPE;
	f cataloge.date_fin%TYPE;
BEGIN
	PERFORM id_produit FROM produit a, cataloge b
	WHERE a.id_produit=NEW.id_produit AND b.id_cataloge=NEW.id_cataloge AND a.reference_local!=0;
	IF FOUND THEN----REFERENCE_LOCAL!=0 ON NE PEUT PAS FAIR LE PROMOTION			
		RAISE NOTICE 'REFRENCE_LOCAL DE PRODUIT ID:% !=0',NEW.id_produit;	
		RETURN NULL;	
	ELSE 
		SELECT date_debut,date_fin FROM cataloge into c,d
		WHERE cataloge.id_cataloge=NEW.id_cataloge;
		PERFORM date_debut,date_fin FROM cataloge,cataloge_produit
		WHERE cataloge.id_cataloge=cataloge_produit.id_cataloge AND cataloge_produit.id_produit=NEW.id_produit 
		AND ((d>=date_debut AND d<=date_fin) OR (c>=date_debut AND c<=date_fin) OR (d>=date_fin AND c<=date_debut));
		IF FOUND THEN---deja existe un period de prom dans ce produit;
			RAISE NOTICE 'date erreur';
			RETURN NULL;
		ELSE
			RETURN NEW;
		END IF;
		
	END IF;	
END;
$$ LANGUAGE 'PLPGSQL';

DROP TRIGGER IF EXISTS m_produit_cataloge ON cataloge_produit;
CREATE TRIGGER m_produit_cataloge
BEFORE INSERT ON cataloge_produit
FOR EACH ROW 
EXECUTE PROCEDURE met_produit_cataloge();

---achat ou supr achat d'un produit----------------------------------------
CREATE OR REPLACE FUNCTION arch_achat() RETURNS TRIGGER AS $$
DECLARE
   i INTEGER;
   a produit.reference_local%TYPE;
   b produit_magasin.prix_vente%TYPE; 
   c cataloge.id_reduit_imme%TYPE;
   d cataloge.id_bon_achat%TYPE;
   e reduit_imme.montant_reduit%TYPE;
   f reduit_imme.pourcentage%TYPE;
   g bon_achat.montant_calcul%TYPE;
   ql produit.quantite_limitee%TYPE;
   qs produit_magasin.quantite_stock%TYPE;
   pa achat_produit.prix_achat_client%TYPE;
   da achat_facture.date_achat%TYPE;
   ic client.id_client%TYPE;
BEGIN
	IF TG_OP = 'DELETE' THEN
		UPDATE achat_facture SET prix_total=prix_total-OLD.prix_achat_client*OLD.quantite_achat WHERE id_achat=OLD.id_achat;
			UPDATE produit_magasin SET quantite_stock=quantite_stock+OLD.quantite_achat 
			WHERE id_produit=OLD.id_produit AND id_magasin=OLD.id_magasin;	
		RETURN NULL;
	ELSE------TG_OP=UPDATE OU INSERT
		SELECT quantite_stock FROM produit_magasin INTO qs WHERE id_produit=NEW.id_produit AND id_magasin=NEW.id_magasin;
		IF NOT FOUND THEN
			RAISE EXCEPTION 'produit % not exist dans magasin %',NEW.id_produit,NEW.id_magasin;
			RETURN NULL;
		ELSE
			IF TG_OP = 'UPDATE' THEN
				IF NEW.id_achat!=OLD.id_achat OR NEW.id_produit!=OLD.id_produit OR NEW.id_magasin!=OLD.id_magasin THEN
					RAISE NOTICE'UPDATE ERREUR DO NOT CHANGER LES ID!!';
					RETURN OLD;
				ELSIF qs<NEW.quantite_achat-OLD.quantite_achat THEN
					RAISE NOTICE'produit % insuffisant dans magasin pendant update%',NEW.id_produit,NEW.id_magasin;
					RETURN OLD;
				ELSE
					SELECT prix_vente FROM produit_magasin INTO b 
					WHERE id_produit=NEW.id_produit AND id_magasin=NEW.id_magasin;
					SELECT reference_local FROM produit INTO c WHERE id_produit=NEW.id_produit;
					IF c!=0 THEN  --les produit ne font pas l'objet de promotion
						RAISE NOTICE'les produit % ne font pas objet de promotion',NEW.id_produit;
						pa:=b;
					ELSE ----les produit peut etre fait le promotion
						SELECT id_reduit_imme,id_bon_achat FROM achat_facture,cataloge_produit cap,cataloge ca INTO c,d
						WHERE NEW.id_produit=cap.id_produit AND ca.id_cataloge=cap.id_cataloge AND NEW.id_achat=achat_facture.id_achat
						AND ca.date_debut<=achat_facture.date_achat AND ca.date_fin>=achat_facture.date_achat;
						IF NOT FOUND THEN
							RAISE NOTICE'PROMOTION DE PRODUIT % NOT EXIST DANS CE PERIOD',NEW.id_produit;
							pa:=b;
						ELSE---------on a le promotion
							SELECT montant_reduit ,pourcentage ,montant_calcul FROM reduit_imme ri ,bon_achat ba INTO e,f,g
							WHERE ri.id_reduit_imme=c AND ba.id_bon_achat=d;
							SELECT quantite_limitee,quantite_stock FROM produit,produit_magasin
							INTO ql,qs
							WHERE produit.id_produit=NEW.id_produit AND produit_magasin.id_produit=NEW.id_produit
							AND produit_magasin.id_magasin=NEW.id_magasin;
							IF qs-NEW.quantite_achat+OLD.quantite_achat>ql THEN----------stock suffisant
								RAISE NOTICE'stock suffisant DE PRODUIT % ',NEW.id_produit;
								pa:=(b-e)*(1-f);
							ELSIF qs-NEW.quantite_achat+OLD.quantite_achat<=ql THEN------stock insuffisant
								RAISE NOTICE'stock insuffisant DE PRODUIT % ',NEW.id_produit;
								pa:=b;
							ELSE
								RAISE NOTICE'stock almost suffisant DE PRODUIT % ',NEW.id_produit;
								pa:=((qs-ql)*(b-e)*(1-f)+(NEW.quantite_achat-OLD.quantite_achat-qs+ql)*b)/(NEW.quantite_achat-OLD.quantite_achat);
							END IF;
						END IF;
					END IF;	
					PERFORM * FROM achat_facture,cataloge_produit cap,cataloge ca
					WHERE NEW.id_produit=cap.id_produit AND ca.id_cataloge=cap.id_cataloge AND NEW.id_achat=achat_facture.id_achat
					AND ca.date_debut<=achat_facture.date_achat AND ca.date_fin>=achat_facture.date_achat;
					IF NOT FOUND THEN
						SELECT date_achat,id_client FROM achat_facture INTO da,ic WHERE id_achat=NEW.id_achat; 
						SELECT EXTRACT(dow FROM da) INTO d;----dimanche
						IF d=0 AND ic%2=1 THEN
							pa:=b*(1-0.05);
							RAISE NOTICE'dimanch reduction pour produit %',NEW.id_produit;
						END IF;
					END IF;
					IF NEW.prix_achat_client!=pa THEN
						RAISE NOTICE'prix_achat de produit % erreur-- on a corregie',NEW.id_produit;
						NEW.prix_achat_client:=pa;
					END IF;
					UPDATE achat_facture 
					SET prix_total=prix_total+NEW.prix_achat_client*NEW.quantite_achat-OLD.prix_achat_client*OLD.quantite_achat
					WHERE id_achat=NEW.id_achat;
					UPDATE produit_magasin SET quantite_stock=quantite_stock-NEW.quantite_achat+OLD.quantite_achat
					WHERE id_produit=NEW.id_produit AND id_magasin=NEW.id_magasin;
					RETURN NEW;
				END IF;
			ELSE----------INSERT-----
				IF qs<NEW.quantite_achat THEN
					RAISE EXCEPTION'produit % insuffisant dans magasin pendant insert%',NEW.id_produit,NEW.id_magasin;
					RETURN NULL;			
				ELSE
					SELECT prix_vente FROM produit_magasin INTO b 
					WHERE id_produit=NEW.id_produit AND id_magasin=NEW.id_magasin;
					SELECT reference_local FROM produit INTO c WHERE id_produit=NEW.id_produit;
					IF c!=0 THEN  --les produit ne font pas l'objet de promotion
						RAISE NOTICE'les produit % ne font pas objet de promotion',NEW.id_produit;
						pa:=b;
					ELSE ----les produit peut etre fait le promotion
						SELECT id_reduit_imme,id_bon_achat FROM achat_facture,cataloge_produit cap,cataloge ca INTO c,d
						WHERE NEW.id_produit=cap.id_produit AND ca.id_cataloge=cap.id_cataloge AND NEW.id_achat=achat_facture.id_achat
						AND ca.date_debut<=achat_facture.date_achat AND ca.date_fin>=achat_facture.date_achat;
						IF NOT FOUND THEN
							RAISE NOTICE'PROMOTION DE PRODUIT % NOT EXIST DANS CE PERIOD',NEW.id_produit;
							pa:=b;
						ELSE---------on a le promotion
							SELECT montant_reduit ,pourcentage ,montant_calcul FROM reduit_imme ri ,bon_achat ba INTO e,f,g
							WHERE ri.id_reduit_imme=c AND ba.id_bon_achat=d;
							SELECT quantite_limitee,quantite_stock FROM produit,produit_magasin
							INTO ql,qs
							WHERE produit.id_produit=NEW.id_produit AND produit_magasin.id_produit=NEW.id_produit
							AND produit_magasin.id_magasin=NEW.id_magasin;
							IF qs-NEW.quantite_achat>ql THEN----------stock suffisant
								RAISE NOTICE'stock suffisant DE PRODUIT % ',NEW.id_produit;
								pa:=(b-e)*(1-f);
							ELSIF qs<=ql THEN------stock insuffisant
								RAISE NOTICE'stock insuffisant DE PRODUIT % ',NEW.id_produit;
								pa:=b;
							ELSE
								RAISE NOTICE'stock almost suffisant DE PRODUIT % ',NEW.id_produit;
								pa:=((qs-ql)*(b-e)*(1-f)+(NEW.quantite_achat-qs+ql)*b)/NEW.quantite_achat;
							END IF;
						END IF;
					END IF;	
					PERFORM * FROM achat_facture,cataloge_produit cap,cataloge ca
					WHERE NEW.id_produit=cap.id_produit AND ca.id_cataloge=cap.id_cataloge AND NEW.id_achat=achat_facture.id_achat
					AND ca.date_debut<=achat_facture.date_achat AND ca.date_fin>=achat_facture.date_achat;
					IF NOT FOUND THEN
						SELECT date_achat,id_client FROM achat_facture INTO da,ic WHERE id_achat=NEW.id_achat; 
						SELECT EXTRACT(dow FROM da) INTO d;----dimanche
						IF d=0 AND ic%2=1 THEN
							pa:=b*(1-0.05);
							RAISE NOTICE'dimanch reduction pour produit %',NEW.id_produit;
						END IF;
					END IF;
					IF NEW.prix_achat_client!=pa THEN
						RAISE NOTICE'prix_achat de produit % erreur-- on a corregie',NEW.id_produit;
						NEW.prix_achat_client:=pa;
					END IF;
					UPDATE achat_facture SET prix_total=prix_total+NEW.prix_achat_client*NEW.quantite_achat 
					WHERE id_achat=NEW.id_achat;
					UPDATE produit_magasin SET quantite_stock=quantite_stock-NEW.quantite_achat 
					WHERE id_produit=NEW.id_produit AND id_magasin=NEW.id_magasin;	
					RETURN NEW;		
				END IF;
			END IF;
		END IF;
	END IF;
	
   
END;
$$ LANGUAGE 'PLPGSQL';
DROP TRIGGER IF EXISTS archive_achat ON achat_produit;
CREATE TRIGGER  archive_achat 
BEFORE INSERT OR UPDATE  ON achat_produit
FOR EACH ROW 
EXECUTE PROCEDURE arch_achat();
DROP TRIGGER IF EXISTS archive_achat_del ON achat_produit;
CREATE TRIGGER  archive_achat_del 
AFTER  DELETE ON achat_produit
FOR EACH ROW 
EXECUTE PROCEDURE arch_achat();

---modifier facture d'un achat----------------------------------------
CREATE OR REPLACE FUNCTION arch_facture() RETURNS TRIGGER AS $$
BEGIN-------------------100$de achat calcule 1 point
	IF TG_OP = 'DELETE' THEN
		UPDATE cartepayement SET credit_calcule=credit_calcule+OLD.montant_payer-OLD.prix_total WHERE id_client=OLD.id_client;
		UPDATE cartefiedlite SET point=point-OLD.montant_payer*0.01 WHERE id_client=OLD.id_client;
		RETURN NULL;
	ELSE ----TG_OP = 'INSERT' OR TG_OP='UPDATE'
		IF TG_OP = 'INSERT' AND NEW.prix_total!=0 THEN
			RAISE NOTICE 'initialiser le prix_total quand on fait le insert ';
			NEW.prix_total:=0;
		END IF;	
		IF NEW.id_client%2!=1 THEN
		--	IF NEW.montant_payer<NEW.prix_total THEN
		--		RAISE NOTICE 'si on na pas le cartepayement,on ne peut pas avoir le credit';
		--		RETURN NULL;
		--	ELSIF NEW.montant_payer>NEW.prix_total THEN
		--		RAISE NOTICE 'on peut achat plus de choise';
		--		RETURN NEW;
		--	ELSE 
				UPDATE cartefiedlite SET point=point+NEW.montant_payer*0.01 WHERE id_client=NEW.id_client;
				RETURN NEW;
		--	END IF;
		ELSE
			UPDATE cartepayement SET credit_calcule=credit_calcule-NEW.montant_payer+NEW.prix_total WHERE id_client=NEW.id_client;
			RETURN NEW;
		END IF;
		IF TG_OP='UPDATE' THEN
			UPDATE cartepayement SET credit_calcule=credit_calcule+OLD.montant_payer-OLD.prix_total WHERE id_client=OLD.id_client;
			UPDATE cartefiedlite SET point=point-OLD.montant_payer*0.01 WHERE id_client=OLD.id_client;
		END IF;
	END IF;
   
END;
$$ LANGUAGE 'PLPGSQL';

DROP TRIGGER IF EXISTS archive_facture ON achat_facture;
CREATE TRIGGER  archive_facture 
BEFORE INSERT OR UPDATE  ON achat_facture
FOR EACH ROW 
EXECUTE PROCEDURE arch_facture();

DROP TRIGGER IF EXISTS archive_facture_del ON achat_facture;
CREATE TRIGGER  archive_facture_del 
AFTER DELETE ON achat_facture
FOR EACH ROW 
EXECUTE PROCEDURE arch_facture();


---archive le credit a la fin du mois----------------------------------------
CREATE OR REPLACE FUNCTION arch_credit() RETURNS TRIGGER AS $$
DECLARE 
	a INTEGER;
BEGIN
	SELECT EXTRACT(day FROM NEW.dates) INTO a;
	IF a=1 THEN
		UPDATE cartepayement SET credit_calcule=credit_calcule*(1+taux_credit);
	END IF;
	RETURN NULL;
END;
$$ LANGUAGE 'PLPGSQL';

DROP TRIGGER IF EXISTS archive_credit ON dates;
CREATE TRIGGER  archive_credit 
AFTER UPDATE ON dates
FOR EACH ROW 
EXECUTE PROCEDURE arch_credit();
