INSERT INTO SCKROL(NOMBREROL, ESTADO, FECHAREGISTRO)
VALUES('ADMIN', true, current_timestamp);

INSERT INTO SCKUSUARIO
(NOMBREUSUARIO, CLAVE, ESTADO, FECHAREGISTRO)
VALUES('admin', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', true, current_timestamp);

INSERT INTO SCKUSUARIOROL
(IDROL, IDUSUARIO, ESTADO, FECHAREGISTRO)
VALUES(1, 1, true, current_timestamp);

INSERT INTO SCKCATEGORIA
(DESCRIPCION, ESTADO, FECHAREGISTRO, VERSION)
VALUES ('Marvel', '1', current_timestamp, 0);

INSERT INTO SCKCATEGORIA
(DESCRIPCION, ESTADO, FECHAREGISTRO, VERSION)
VALUES ('DC comics', '1', current_timestamp, 0);


INSERT INTO SCKARTICULO
(IDCATEGORIA, CODIGOBARRAS, DESCRIPCION, PRECIO, EXISTENCIA, ESTADO, FECHAREGISTRO, VERSION)
VALUES (2,'72111111', 'J. Carro Batman', 7, 7, '1', current_timestamp, 0);

INSERT INTO SCKARTICULO
(IDCATEGORIA, CODIGOBARRAS, DESCRIPCION, PRECIO, EXISTENCIA, ESTADO, FECHAREGISTRO, VERSION)
VALUES (2,'7222222', 'Camiseta Batman', 10, 7, '1', current_timestamp, 0);

INSERT INTO SCKARTICULO
(IDCATEGORIA, CODIGOBARRAS, DESCRIPCION, PRECIO, EXISTENCIA, ESTADO, FECHAREGISTRO, VERSION)
VALUES (2,'7333333', 'Vaso Batman', 7, 7, '1', current_timestamp, 0);

INSERT INTO SCKARTICULO
(IDCATEGORIA, CODIGOBARRAS, DESCRIPCION, PRECIO, EXISTENCIA, ESTADO, FECHAREGISTRO, VERSION)
VALUES (1,'7333333', 'Vaso Iron man', 7, 7, '1', current_timestamp, 0);


