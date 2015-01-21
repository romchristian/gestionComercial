CREATE OR REPLACE VIEW groups AS 
 SELECT cast(lower(r.nombre) as character varying(255)) AS groupid, u.usuario AS userid
   FROM rol r
   JOIN usuario u ON r.id = u.rol_id;

ALTER TABLE groups OWNER TO postgres;



CREATE OR REPLACE VIEW users AS 
 SELECT usuario.usuario AS userid, usuario.clave AS password
   FROM usuario;

ALTER TABLE users OWNER TO postgres;