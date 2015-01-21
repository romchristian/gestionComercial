alter table facturarec ADD column totalgravada10 double precision;
alter table facturarec ADD column totalgravada05 double precision;
alter table facturarec ADD column totaliva10 double precision;
alter table facturarec ADD column totaliva05 double precision;
alter table facturarec ADD column timbrado bigint;


alter table  detfacturarec ADD column gravada10 double precision;
alter table  detfacturarec ADD column gravada05 double precision;
alter table  detfacturarec ADD column exenta double precision;
alter table  detfacturarec ADD column total double precision;
alter TABLE  proveedor add column ruc text;
alter TABLE  proveedor add column dv integer;





CREATE TABLE proveedortimbrado
(
  id bigint NOT NULL,
  codest character varying(255),
  codsuc character varying(255),
  estado character varying(255),
  timbrado bigint,
  vigencia date,
  proveedor bigint,
  CONSTRAINT proveedortimbrado_pkey PRIMARY KEY (id),
  CONSTRAINT fk_proveedortimbrado_proveedor_id FOREIGN KEY (proveedor)
      REFERENCES proveedor (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE proveedortimbrado
  OWNER TO diestra;