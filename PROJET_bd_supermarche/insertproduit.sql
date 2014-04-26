

-------insert date------------------------------------------------------
INSERT INTO dates(dates) VALUES ('2010-10-18');
-------insert categorie
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (1, 'poisson', 1);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (11, 'biere', 1);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (12, 'coca', 1);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (2, 'meuble', 2);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (21, 'chair', 2);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (22, 'table', 2);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (23, 'sofa', 2);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (3, 'aliments', 3);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (31, 'legume', 3);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (32, 'pain', 3);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (33, 'riz', 3);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (34, 'sauce', 3);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (4, 'electrique', 4);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (41, 'tele', 4);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (42, 'ordinateur', 4);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (43, 'frigo', 4);
INSERT INTO categorie(id_categorie, nom_categorie, id_souscategorie) VALUES (44, 'climatiseur',4);
-------------insert magasin
INSERT INTO magasin(id_magasin, address_magasin) VALUES (1, 'paris');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (2, 'toulous');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (3, 'clermont');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (4, 'lyon');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (5, 'paris-sud');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (6, 'paris-est');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (7, 'paris-centre');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (8, 'nante');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (9, 'bezier');
INSERT INTO magasin(id_magasin, address_magasin) VALUES (10, 'paris-didro');

----------------INSERT PRODUIT
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (1, 'A', 1.5, 1, '2010-12-30', 12,34);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (2, 'B', 18.58, 2, '2011-12-30', 11,45);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (3, 'C', 71.58, 3, '2012-12-30', 11,56);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (4, 'D', 71.59, 4, '2011-12-30', 11,77);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (5, 'E', 17.50, 5, '2010-12-30', 12,100);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (6, 'F', 4145.56, 6, '2010-12-30', 12,200);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (7, 'G', 31.57, 0, '2011-06-30', 12,10);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (8, 'H', 12.50, 7, '2012-12-30', 12,34);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (9, 'I', 13.59, 0, '2014-12-30', 22,45);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (10, 'J', 441.53, 8, '2010-12-30', 22,46);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (11, 'K', 15.54, 9, '2010-12-30', 21,56);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (12, 'L', 14.55, 0, '2012-12-30', 31,566);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (13, 'M', 15.45, 5, '3010-07-30', 23,567);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (14, 'N', 14.55, 0, '2010-12-30', 42,433);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (15, 'O', 13.54, 2, '2011-12-25', 21,34);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (16, 'P', 134.55, 3, '3010-12-30', 21,66);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (17, 'Q', 13.5, 0, '2010-12-30', 22,576);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (18, 'R', 1.53, 2, '3010-12-12', 32,66);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (19, 'S', 134.5, 0, '3010-12-30', 22,56);
INSERT INTO produit(id_produit, nom_produit, prix_achat, reference_local, date_peremption, id_categorie,quantite_limitee) VALUES (20, 'T', 12.5, 5, '3010-12-30', 22,2303);

---------------insert produit_magasin
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (1, 1, 133.5, 54);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (2, 2, 223.6, 246);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (3, 3, 34.6, 236);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (4, 4, 345.5, 437);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (5, 5, 345.7, 3438);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (6, 6, 32400.7, 4400);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (7, 7, 34.7, 15);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (8, 8, 667.8, 4577);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (9, 9, 456.8, 3477);
INSERT INTO produit_magasin(id_magasin, id_produit, prix_vente, quantite_stock) VALUES (10,10, 10044, 3455);
------------insert reduit_imme
INSERT INTO reduit_imme(id_reduit_imme, montant_reduit, pourcentage) VALUES (1, 0, 0.2);
INSERT INTO reduit_imme(id_reduit_imme, montant_reduit, pourcentage) VALUES (2, 0, 0.3);
INSERT INTO reduit_imme(id_reduit_imme, montant_reduit, pourcentage) VALUES (3, 0, 0.4);
INSERT INTO reduit_imme(id_reduit_imme, montant_reduit, pourcentage) VALUES (4, 145, 0);
INSERT INTO reduit_imme(id_reduit_imme, montant_reduit, pourcentage) VALUES (5, 134, 0);
INSERT INTO reduit_imme(id_reduit_imme, montant_reduit, pourcentage) VALUES (0, 0, 0);
----------insert bon_achat
INSERT INTO bon_achat(id_bon_achat, montant_calcul) VALUES (0, 0);
INSERT INTO bon_achat(id_bon_achat, montant_calcul) VALUES (1, 34);
INSERT INTO bon_achat(id_bon_achat, montant_calcul) VALUES (2, 235);
INSERT INTO bon_achat(id_bon_achat, montant_calcul) VALUES (3, 56);
INSERT INTO bon_achat(id_bon_achat, montant_calcul) VALUES (4, 344);
INSERT INTO bon_achat(id_bon_achat, montant_calcul) VALUES (5, 34);
------------insert cataloge
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (1, '2010-10-05','2010-10-11',1,1);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (2, '2010-10-07','2010-10-13',2,1);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (3, '2010-10-12','2010-10-18',1,3);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (4, '2010-10-16','2010-10-22',1,4);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (5, '2010-10-20','2010-10-26',0,1);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (6, '2010-11-05','2010-11-11',5,0);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (7, '2010-11-05','2010-11-11',1,1);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (8, '2010-11-07','2010-11-13',2,1);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (9, '2010-09-05','2010-09-11',1,3);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (10, '2010-12-05','2010-12-11',0,4);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (11, '2010-12-26','2011-01-01',0,1);
INSERT INTO cataloge(id_cataloge, date_debut, date_fin, id_reduit_imme, id_bon_achat) VALUES (12, '2010-12-15','2010-12-21',5,0);
----------insert produit cataloge---- 
INSERT INTO cataloge_produit(id_cataloge, id_produit) VALUES (9, 7);
INSERT INTO cataloge_produit(id_cataloge, id_produit) VALUES (4, 7);
INSERT INTO cataloge_produit(id_cataloge, id_produit) VALUES (11, 7);
INSERT INTO cataloge_produit(id_cataloge, id_produit) VALUES (1, 9);
INSERT INTO cataloge_produit(id_cataloge, id_produit) VALUES (5, 9);
INSERT INTO cataloge_produit(id_cataloge, id_produit) VALUES (12, 9);
INSERT INTO cataloge_produit(id_cataloge, id_produit) VALUES (2, 14);
INSERT INTO cataloge_produit(id_cataloge, id_produit) VALUES (3, 17);
------------insert client-----id_client=0 si les clients sont anonymes
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (0, 'ANONYMES','anonymes');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (1, 'PENG','bo');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (2, 'BERNARD','Sadu');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (3, 'B', 'a');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (4, 'C', 'b');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (5, 'D', 'c');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (6, 'E', 'd');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (7, 'F', 'e');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (8, 'G', 'f');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (9, 'H', 'g');
INSERT INTO client(id_client, nom_client, prenom_client) VALUES (10, 'I', 'h');
---------------insert cartefiedlite----------------------id_client pair
INSERT INTO cartefiedlite(id_client, point) VALUES (2, 0);
INSERT INTO cartefiedlite(id_client, point) VALUES (4, 0);
INSERT INTO cartefiedlite(id_client, point) VALUES (6, 0);
INSERT INTO cartefiedlite(id_client, point) VALUES (8, 100);
INSERT INTO cartefiedlite(id_client, point) VALUES (10, 20);
-----------------insert cartepayement--------------------------id_client impair
INSERT INTO cartepayement(id_client, credit_calcule, taux_credit) VALUES (1, 1000, 0.03);
INSERT INTO cartepayement(id_client, credit_calcule, taux_credit) VALUES (3, 0, 0.03);
INSERT INTO cartepayement(id_client, credit_calcule, taux_credit) VALUES (5, 0, 0.03);
INSERT INTO cartepayement(id_client, credit_calcule, taux_credit) VALUES (7, 500, 0.03);
INSERT INTO cartepayement(id_client, credit_calcule, taux_credit) VALUES (9, 10, 0.03);
-------------insert achat_facture--------------------
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (1, 0, '2010-12-10', 0, 347);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (2, 1, '2010-12-12', 0, 162300);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (3, 2, '2010-12-12', 0, 11753.8);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (4, 3, '2010-12-13', 0, 452);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (5, 4, '2010-12-14', 0, 34.6);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (6, 5, '2010-12-15', 0, 343);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (7, 6, '2010-12-16', 0, 4272);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (8, 7, '2010-12-17', 0, 3455);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (9, 8, '2010-12-18', 0, 0);
INSERT INTO achat_facture(id_achat, id_client, date_achat, prix_total, montant_payer) VALUES (10, 9, '2010-12-19', 0, 2345);
-------------------insert  achat_produit
INSERT INTO achat_produit(id_achat, id_magasin, id_produit, quantite_achat, prix_achat_client) VALUES (1, 7, 7, 10, 0);
INSERT INTO achat_produit(id_achat, id_magasin, id_produit, quantite_achat, prix_achat_client) VALUES (2, 6, 6, 5, 0);
INSERT INTO achat_produit(id_achat, id_magasin, id_produit, quantite_achat, prix_achat_client) VALUES (3, 5, 5, 34, 0);
INSERT INTO achat_produit(id_achat, id_magasin, id_produit, quantite_achat, prix_achat_client) VALUES (4, 4, 4, 3, 0);
INSERT INTO achat_produit(id_achat, id_magasin, id_produit, quantite_achat, prix_achat_client) VALUES (5, 3, 3, 1, 0);
INSERT INTO achat_produit(id_achat, id_magasin, id_produit, quantite_achat, prix_achat_client) VALUES (6, 2, 2, 56, 0);
INSERT INTO achat_produit(id_achat, id_magasin, id_produit, quantite_achat, prix_achat_client) VALUES (7, 1, 1, 32, 0);
