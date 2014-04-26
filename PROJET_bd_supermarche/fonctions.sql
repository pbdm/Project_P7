---------------------function return id de produit qui est le plus vendu
CREATE OR REPLACE FUNCTION produit_plus_vendu() RETURNS INTEGER AS $$
DECLARE
	produit RECORD;
	ip achat_produit.id_produit%TYPE;
	pt achat_produit.prix_achat_client%TYPE:=0;
BEGIN
	FOR produit IN SELECT id_produit,sum(prix_achat_client*quantite_achat) as sum FROM achat_produit group by id_produit
	LOOP
		IF pt<produit.sum THEN
			pt:=produit.sum;
			ip:=produit.id_produit;
		END IF;	
	END LOOP;
	RETURN ip;
END;
$$ LANGUAGE 'plpgsql';

--select produit_plus_vendu();

---------------------function return id de produit qui est le plus vendu hor promotion
CREATE OR REPLACE FUNCTION produit_plus_vendu_hp() RETURNS INTEGER AS $$
DECLARE
	p RECORD;
	ip achat_produit.id_produit%TYPE;
	pt achat_produit.prix_achat_client%TYPE:=0;
BEGIN
	FOR p IN SELECT achat_produit.id_produit,sum(prix_achat_client*quantite_achat) as sum FROM achat_produit,produit_magasin
	WHERE achat_produit.id_produit=produit_magasin.id_produit AND achat_produit.id_magasin=produit_magasin.id_magasin 
	AND produit_magasin.prix_vente=achat_produit.prix_achat_client group by achat_produit.id_produit
	LOOP
		
			IF pt<p.sum THEN
				pt:=p.sum;
				ip:=p.id_produit;
			END IF;	
		
	END LOOP;
	RETURN ip;
END;
$$ LANGUAGE 'plpgsql';
--select produit_plus_vendu_hp();

---------------------function return id de produit qui est le plus rentable en volume
CREATE OR REPLACE FUNCTION produit_plus_rentable_vol() RETURNS INTEGER AS $$
DECLARE
	p RECORD;
	ip achat_produit.id_produit%TYPE;
	pt achat_produit.prix_achat_client%TYPE:=0;
BEGIN
	FOR p IN SELECT achat_produit.id_produit,sum((prix_achat_client-prix_achat)*quantite_achat)/sum(quantite_achat) as sum FROM achat_produit, produit
	WHERE achat_produit.id_produit=produit.id_produit GROUP BY achat_produit.id_produit 
	LOOP
		IF pt<p.sum THEN
			pt:=p.sum;
			ip:=p.id_produit;
		END IF;		
	END LOOP;
	RETURN ip;
END;
$$ LANGUAGE 'plpgsql';

select produit_plus_rentable_vol();


--------------------function return id de produit qui est le plus rentable en qualite
CREATE OR REPLACE FUNCTION produit_plus_rentable_qual(INTEGER) RETURNS INTEGER AS $$
DECLARE
	p RECORD;
	ip achat_produit.id_produit%TYPE;
	pt achat_produit.prix_achat_client%TYPE:=0;
BEGIN
	IF $1%2=1 THEN----CARTEPAYEMENT
		FOR p IN SELECT achat_produit.id_produit,sum((prix_achat_client-prix_achat)*quantite_achat) as sum 
		FROM achat_produit, produit,achat_facture
		WHERE achat_produit.id_produit=produit.id_produit AND achat_facture.id_achat=achat_produit.id_achat AND id_client%2=1
		GROUP BY achat_produit.id_produit 
		LOOP
			IF pt<p.sum THEN
				pt:=p.sum;
				ip:=p.id_produit;
			END IF;		
		END LOOP;
		RETURN ip;
	
	ELSE 
		IF $1=0 THEN   ---anonyme
			FOR p IN SELECT achat_produit.id_produit,sum((prix_achat_client-prix_achat)*quantite_achat) as sum 
			FROM achat_produit, produit,achat_facture
			WHERE achat_produit.id_produit=produit.id_produit AND achat_facture.id_achat=achat_produit.id_achat AND id_client=0
			GROUP BY achat_produit.id_produit 
			LOOP
				IF pt<p.sum THEN
					pt:=p.sum;
					ip:=p.id_produit;
				END IF;		
			END LOOP;
			RETURN ip;
		ELSE  ---cartefielite
			FOR p IN SELECT achat_produit.id_produit,sum((prix_achat_client-prix_achat)*quantite_achat) as sum 
			FROM achat_produit, produit,achat_facture
			WHERE achat_produit.id_produit=produit.id_produit AND achat_facture.id_achat=achat_produit.id_achat AND id_client%2=0 AND id_client!=0
			GROUP BY achat_produit.id_produit 
			LOOP
				IF pt<p.sum THEN
					pt:=p.sum;
					ip:=p.id_produit;
				END IF;		
			END LOOP;
			RETURN ip;
		END IF;
	END IF;
		
END;
$$ LANGUAGE 'plpgsql';



--------------------function return pour un euro d¨¦pens¨¦ quel est le retour pour le magasin
CREATE OR REPLACE FUNCTION marge_mag(INTEGER) RETURNS FLOAT AS $$
DECLARE
	a FLOAT;
BEGIN
	SELECT sum((prix_achat_client-prix_achat)*quantite_achat)/sum(prix_achat_client*quantite_achat) INTO a
	FROM achat_produit, produit
	WHERE achat_produit.id_produit=produit.id_produit AND $1=achat_produit.id_magasin
	GROUP BY achat_produit.id_magasin;
	RETURN a;
END;
$$ LANGUAGE 'plpgsql';

----------------function cacle le panier du client
create or replace function panier_client(cl_id achat_facture.id_client%type) returns float as $$
declare
	moyen float;
	--cl_id alias for $1;
begin
	select sum(prix_total)/ count(*) into moyen from achat_facture a where id_client=cl_id group by id_client;
	if found then
		return moyen;
	else
		return null;
	end if;

end; 
$$ LANGUAGE 'plpgsql';
	
