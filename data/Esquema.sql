
-- Creaci�n del secuenciador
create sequence superAndes_sequence;
-- Creaaci�n de la tabla PRODUCTO y especificaci�n de sus restricciones
CREATE TABLE A_PRODUCTO
   (
       NOMBRE VARCHAR (255 BYTE),
       MARCA VARCHAR (255 BYTE),
       PRECIOUNITARIO NUMBER(*,2), --EL PRIMER PARAMETRO DEL PARENTESIS ES LA CANTIDAD DE DIGITOS
       --EL SEGUNDO ES LA CANTIDAD DE CIFRAS DESPUES DE LA COMA
        PRESENTACION VARCHAR (255 BYTE),
        PRECIOUNIDADMEDIDA NUMBER (*,2),
        UNIDADDEMEDIDA VARCHAR (255 BYTE),
        ESPECIFIACIONEMPACADO VARCHAR (255 BYTE),
        CODIGOBARRAS NUMBER (*,0),
        CATEGORIA VARCHAR (255 BYTE),
        TIPO VARCHAR (255 BYTE),
        PRECIOVENTA NUMBER (*,2),
        CONSTRAINT A_PRODUCTO_PK PRIMARY KEY (CODIGOBARRAS)
   );
ALTER TABLE A_PRODUCTO
	ADD CONSTRAINT NUMEROS_POSITIVOS
	CHECK 
    (
        PRECIOUNITARIO>0 
         AND PRECIOUNIDADMEDIDA>0
         AND PRECIOVENTA >0
         AND CODIGOBARRAS >0
    )
ENABLE;

-- Creaaci�n de la tabla PROVEEDOR y especificaci�n de sus restricciones
CREATE TABLE A_PROVEEDOR
(
    NIT NUMBER (*,0),
    NOMBRE VARCHAR(255 BYTE),
    CALIFICACIONCALIDAD NUMBER(4,2),
    CONSTRAINT A_PROVEEDOR_PK PRIMARY KEY (NIT)
);
ALTER TABLE A_PROVEEDOR
ADD CONSTRAINT NUMERO_POSITIVO
CHECK 
    (
        NIT>0 
         AND CALIFICACIONCALIDAD>0
    )
ENABLE;
-- Creación de la tabla SUCURSAL y especificación de sus restricciones
CREATE TABLE A_SUCURSAL
(
    ID NUMBER,
    CIUDAD VARCHAR( 255 BYTE),
    DIRECCION VARCHAR ( 255 BYTE ),
    NOMBRE VARCHAR ( 255 BYTE ),
    CONSTRAINT A_SUCURSAL_PK PRIMARY KEY (ID)
);
-- Creación de la tabla CLIENTE y especificación de sus restricciones
CREATE TABLE A_CLIENTE
(
    NOMBRE VARCHAR(255 BYTE),
    CORREOELECTRONICO VARCHAR(255 BYTE),
    CONSTRAINT A_CLIENTE_PK PRIMARY KEY (CORREOELECTRONICO)
);

-- Creación de la tabla EMPRESA y especificación de sus restricciones
CREATE TABLE A_EMPRESA
    (
    NIT NUMBER ,
    NOMBRE VARCHAR(255 BYTE) ,
    DIRECCION VARCHAR (255 BYTE) ,
    CORREO VARCHAR(255 BYTE),
    CONSTRAINT A_EMPRESA_PK PRIMARY KEY (CORREO)
    );
ALTER TABLE A_EMPRESA
ADD CONSTRAINT FK_E_CORREO
    FOREIGN KEY (CORREO)
    REFERENCES A_CLIENTE(CORREOELECTRONICO)
ENABLE;


ALTER TABLE A_EMPRESA
ADD CONSTRAINT NIT_POSITIVO
CHECK 
    (
        NIT>0 
    )
ENABLE;
-- Creación de la tabla CLIENTE y especificación de sus restricciones
CREATE TABLE A_PERSONA
(
    NOMBRE VARCHAR(255 BYTE),
    CORREO VARCHAR(255 BYTE),
    CEDULA NUMBER,
    CONSTRAINT A_PERSONA_PK PRIMARY KEY (CORREO)
);

ALTER TABLE A_PERSONA
ADD CONSTRAINT FK_P_CORREO
    FOREIGN KEY (CORREO)
    REFERENCES A_CLIENTE(CORREOELECTRONICO)
ENABLE;

ALTER TABLE A_PERSONA
ADD CONSTRAINT CEDULA_POSITIVO
CHECK 
    (
        CEDULA>0 
    )
ENABLE;

-- Creación de la tabla ESTANTE y especificación de sus restricciones
CREATE TABLE A_ESTANTE
(
   ID NUMBER,
   CANTIDAD NUMBER,
   VOLUMEN NUMBER,
   NIVELABASTECIMIENTO NUMBER,
   CANTIDADMINIMA NUMBER,
   PESO NUMBER,
   TIPO VARCHAR (255 BYTE),
   CONSTRAINT A_ESTANTE_PK PRIMARY KEY (ID)

);

ALTER TABLE A_ESTANTE
ADD CONSTRAINT VERIFICARPOSITIVOS
CHECK 
(
    PESO >0
    AND CANTIDADMINIMA > 0
    AND NIVELABASTECIMIENTO > 0
    AND VOLUMEN >0
    AND CANTIDAD > 0
);
-- Creación de la tabla BODEGA y especificación de sus restricciones
CREATE TABLE A_BODEGA
(
    ID NUMBER,
    CANTIDAD NUMBER,
    VOLUMEN NUMBER,
    CANTIDADMINIMA NUMBER, 
    PESO NUMBER, 
    TIPO VARCHAR (255 BYTE),
    CONSTRAINT A_BODEGA_PK PRIMARY KEY (ID)
);

ALTER TABLE A_BODEGA
ADD CONSTRAINT BODEGA_POSITIVOS
    CHECK
    (
        PESO > 0
        AND CANTIDADMINIMA > 0
        AND VOLUMEN > 0
        AND CANTIDAD > 0
    ) 
ENABLE;   
-- Creación de la tabla ORDENPEDIDO y especificación de sus restricciones
CREATE TABLE A_ORDENPEDIDO
(
    ID NUMBER,
    IDPRODUCTO NUMBER,
    VOLUMEN NUMBER, 
    FECHAENTRAGA DATE,
    ENTREGADO NUMBER(1,0), 
    CALIFICACION NUMBER, 
    IDPROVEEDOR NUMBER,
    CONSTRAINT A_ORDENPEDIDO_FK PRIMARY KEY (ID)
);
ALTER TABLE A_ORDENPEDIDO
ADD CONSTRAINT FK_OP_PRODUCTO
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES A_PRODUCTO(CODIGOBARRAS)
ENABLE;

ALTER TABLE A_ORDENPEDIDO
ADD CONSTRAINT FK_OP_PROVEEDOR
    FOREIGN KEY (IDPROVEEDOR)
    REFERENCES A_PROVEEDOR(NIT)
ENABLE;

ALTER TABLE A_ORDENPEDIDO
ADD CONSTRAINT NUMEROS_OP_POSITIVOS
    CHECK
    (
        VOLUMEN > 0
    )
ENABLE;
ALTER TABLE A_ORDENPEDIDO
	ADD CONSTRAINT CK_OP_ENTREGADO 
	CHECK (ENTREGADO IN (1,0))
ENABLE;
-- Creación de la tabla FACTURA y especificación de sus restricciones
CREATE TABLE A_FACTURA
(
    ID NUMBER,
    FECHA DATE,
    VALORTOTAL NUMBER,
    CONSTRAINT A_FACTURA_PK  PRIMARY KEY (ID)
);
ALTER TABLE A_FACTURA
ADD CONSTRAINT FACTURA_POSITIVOS
    CHECK
    (
        VALORTOTAL >0 
    )
ENABLE;

-- Creación de la tabla FACTURA_CLIENTE y especificación de sus restricciones
CREATE TABLE A_FACTURACLIENTE
(
    IDCLIENTE VARCHAR(255 BYTE),
    IDFACTURA NUMBER,
    CONSTRAINT A_FACTURACLIENTE_PK PRIMARY KEY ( IDCLIENTE, IDFACTURA)
);
ALTER TABLE A_FACTURACLIENTE
ADD CONSTRAINT FK_FC_CLIENTE
    FOREIGN KEY (IDCLIENTE)
    REFERENCES A_CLIENTE(CORREOELECTRONICO)
ENABLE;

ALTER TABLE A_FACTURACLIENTE
ADD CONSTRAINT FK_FC_FACTURA
    FOREIGN KEY (IDFACTURA)
    REFERENCES A_FACTURA(ID)
ENABLE;


-- Creación de la tabla CANTIDADVENDIDA y especificación de sus restricciones
CREATE TABLE A_CANTIDADVENDIDA
(
    IDFACTURA NUMBER,
    IDPRODUCTO NUMBER,
    CANTIDAD NUMBER,
    CONSTRAINT A_CANTIDADVENDIDA_PK PRIMARY KEY (IDFACTURA, IDPRODUCTO)
);
ALTER TABLE A_CANTIDADVENDIDA
ADD CONSTRAINT FK_CV_PRODUCTO
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES A_PRODUCTO(CODIGOBARRAS)
ENABLE;

ALTER TABLE A_CANTIDADVENDIDA
ADD CONSTRAINT FK_CV_FACTURA
    FOREIGN KEY (IDFACTURA)
    REFERENCES A_FACTURA(ID)
ENABLE;

ALTER TABLE A_CANTIDADVENDIDA
ADD CONSTRAINT CANTIDADPOSITIVA
    CHECK
    (
        CANTIDAD > 0
    )
ENABLE;
-- Creación de la tabla SUCURSAL_PRODUCTO y especificación de sus restricciones
CREATE TABLE A_SUCURSALPRODUCTO
(
    IDSUCURSAL NUMBER,
    IDPRODUCTO NUMBER,
    CONSTRAINT A_SUCURSALPRODUCTO_PK PRIMARY KEY (IDSUCURSAL, IDPRODUCTO)
);
ALTER TABLE A_SUCURSALPRODUCTO
ADD CONSTRAINT FK_SP_SUCURSAL
    FOREIGN KEY (IDSUCURSAL) 
    REFERENCES A_SUCURSAL(ID)
ENABLE;
ALTER TABLE A_SUCURSALPRODUCTO
ADD CONSTRAINT FK_SC_PRODUCTO
    FOREIGN KEY (IDPRODUCTO) 
    REFERENCES A_PRODUCTO(CODIGOBARRAS)
ENABLE;

-- Creación de la tabla SUCURSAL_CLIENTE y especificación de sus restricciones
CREATE TABLE A_SUCURSALCLIENTE
(
    IDSUCURSAL NUMBER,
    IDCLIENTE VARCHAR (255 BYTE),
    CONSTRAINT A_SUCURSAL_CLIENTE_PK PRIMARY KEY (IDSUCURSAL, IDCLIENTE)
);
ALTER TABLE A_SUCURSALCLIENTE
ADD CONSTRAINT FK_SC_SUCURSAL
    FOREIGN KEY (IDSUCURSAL) 
    REFERENCES A_SUCURSAL(ID)
ENABLE;
ALTER TABLE A_SUCURSALCLIENTE
ADD CONSTRAINT FK_SC_CLIENTE
    FOREIGN KEY (IDCLIENTE) 
    REFERENCES A_CLIENTE(CORREOELECTRONICO)
ENABLE;


-- Creación de la tabla SUCURSAL_PROVEEDOR y especificación de sus restricciones
CREATE TABLE A_SUCURSALPROVEEDOR
(
    IDSUCURSAL NUMBER,
    IDPROVEEDOR NUMBER,
    CONSTRAINT A_SUCURSALPROVEEDOR_PK PRIMARY KEY (IDSUCURSAL, IDPROVEEDOR)
);
ALTER TABLE A_SUCURSALPROVEEDOR
ADD CONSTRAINT FK_SPRO_SUCURSAL
    FOREIGN KEY (IDSUCURSAL) 
    REFERENCES A_SUCURSAL(ID)
ENABLE;

ALTER TABLE A_SUCURSALPROVEEDOR
ADD CONSTRAINT FK_SP_PROVEEDOR
    FOREIGN KEY (IDPROVEEDOR) 
    REFERENCES A_PROVEEDOR(NIT)
ENABLE;

-- Creación de la tabla SUCURSAL_ESTANTE y especificación de sus restricciones
CREATE TABLE A_SUCURSALESTANTE
(
    IDSUCURSAL NUMBER,
    IDESTANTE NUMBER,
    CONSTRAINT A_SUCURSAL_ESTANTE_PK PRIMARY KEY (IDSUCURSAL, IDESTANTE)
);
ALTER TABLE A_SUCURSALESTANTE
ADD CONSTRAINT FK_SE_SUCURSAL
    FOREIGN KEY (IDSUCURSAL) 
    REFERENCES A_SUCURSAL(ID)
ENABLE;

ALTER TABLE A_SUCURSALESTANTE
ADD CONSTRAINT FK_SE_ESTANTE
    FOREIGN KEY (IDESTANTE) 
    REFERENCES A_ESTANTE(ID)
ENABLE;


-- Creación de la tabla SUCURSAL_BODEGA y especificación de sus restricciones
CREATE TABLE A_SUCURSALBODEGA
(
    IDSUCURSAL NUMBER,
    IDBODEGA NUMBER,
    CONSTRAINT A_SUCURSALBODEGA_PK PRIMARY KEY (IDSUCURSAL, IDBODEGA)
);
ALTER TABLE A_SUCURSALBODEGA
ADD CONSTRAINT FK_SB_SUCURSAL
    FOREIGN KEY (IDSUCURSAL) 
    REFERENCES A_SUCURSAL(ID)
ENABLE;
ALTER TABLE A_SUCURSALBODEGA
ADD CONSTRAINT FK_SB_BODEGA
    FOREIGN KEY (IDBODEGA) 
    REFERENCES A_BODEGA(ID)
ENABLE;

-- Creación de la tabla SUCURSAL_PEDIDO y especificación de sus restricciones
CREATE TABLE A_SUCURSALPEDIDO
(
    IDSUCURSAL NUMBER,
    IDPEDIDO NUMBER,
    CONSTRAINT A_SUCURSAL_PEDIDO_PK PRIMARY KEY (IDSUCURSAL, IDPEDIDO)
);
ALTER TABLE A_SUCURSALPEDIDO
ADD CONSTRAINT FK_SPE_SUCURSAL
    FOREIGN KEY (IDSUCURSAL) 
    REFERENCES A_SUCURSAL(ID)
ENABLE;
ALTER TABLE A_SUCURSALPEDIDO
ADD CONSTRAINT FK_SPE_PEDIDO
    FOREIGN KEY (IDPEDIDO) 
    REFERENCES A_ORDENPEDIDO(ID)
ENABLE;

-- Creación de la tabla CANTIDADENBODEGA y especificación de sus restricciones
CREATE TABLE A_CANTIDADENBODEGA
(
    IDPRODUCTO NUMBER,
    IDBODEGA NUMBER,
    CANTIDAD NUMBER,
    CONSTRAINT A_CANTIDADENBODEGA_PK PRIMARY KEY (IDBODEGA, IDPRODUCTO)
);
ALTER TABLE A_CANTIDADENBODEGA
ADD CONSTRAINT FK_CEB_PRODUCTO
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES A_PRODUCTO(CODIGOBARRAS)
ENABLE;

ALTER TABLE A_CANTIDADENBODEGA
ADD CONSTRAINT FK_CEB_BODEGA
    FOREIGN KEY (IDBODEGA)
    REFERENCES A_BODEGA(ID)
ENABLE;

ALTER TABLE A_CANTIDADENBODEGA
ADD CONSTRAINT CEB_CANTIDADPOSITIVA
    CHECK
    (
        CANTIDAD > 0
    )
ENABLE;

-- Creación de la tabla CANTIDADENESTANTE y especificación de sus restricciones
CREATE TABLE A_CANTIDADENESTANTE
(
    IDPRODUCTO NUMBER,
    IDESTANTE NUMBER,
    CANTIDAD NUMBER,
    CONSTRAINT A_CANTIDADENESTANTE_PK PRIMARY KEY (IDESTANTE, IDPRODUCTO)
);
ALTER TABLE A_CANTIDADENESTANTE
ADD CONSTRAINT FK_CEE_PRODUCTO
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES A_PRODUCTO(CODIGOBARRAS)
ENABLE;

ALTER TABLE A_CANTIDADENESTANTE
ADD CONSTRAINT FK_CEE_ESTANTE
    FOREIGN KEY (IDESTANTE)
    REFERENCES A_ESTANTE(ID)
ENABLE;

ALTER TABLE A_CANTIDADENBODEGA
ADD CONSTRAINT CEE_CANTIDADPOSITIVA
    CHECK
    (
        CANTIDAD > 0
    )
ENABLE;
-- Creación de la tabla Promocion y especificación de sus restricciones
CREATE TABLE A_PROMOCION 
(
    ID NUMBER,
    FEHCAINICIO DATE,
    FECHADETERMINACION DATE,
    DESCRIPCION VARCHAR(255 BYTE),
    UNIDADESDISPONIBLES NUMBER, 
    CONSTRAINT A_PROMOCION_PK PRIMARY KEY (ID) 
);
ALTER TABLE A_PROMOCION
ADD CONSTRAINT FK_P_DISPONIBLESPOSITIVO
    CHECK 
      (
            UNIDADESDISPONIBLES >0
      )
ENABLE;
-- Creación de la tabla A_Proveedor_producto y especificación de sus restricciones
CREATE TABLE A_PROVEEDORPRODUCTO
(
    IDPROVEEDOR NUMBER,
    IDPRODUCTO NUMBER,
    CONSTRAINT PROVEEDORPRODUCTO_PK PRIMARY KEY (IDPROVEEDOR, IDPRODUCTO)
);
ALTER TABLE A_PROVEEDORPRODUCTO
ADD CONSTRAINT FK_PP_PROVEEDOR
    FOREIGN KEY (IDPROVEEDOR)
    REFERENCES A_PROVEEDOR(NIT)
ENABLE;
ALTER TABLE A_PROVEEDORPRODUCTO
ADD CONSTRAINT FK_PP_PRODUCTO
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES A_PRODUCTO(CODIGOBARRAS)
ENABLE;

-- Creación de la tabla A_PROMOCIONVENDIDOS y especificación de sus restricciones
CREATE TABLE A_PROMOCIONVENDIDOS
(
    IDPRODUCTO NUMBER ,
    IDPROMOCION NUMBER ,
    CANTIDAD NUMBER NOT NULL,
    CONSTRAINT PROMOCIONVENDIDOS_PK PRIMARY KEY (IDPRODUCTO, IDPROMOCION)
);
ALTER TABLE A_PROMOCIONVENDIDOS
ADD CONSTRAINT FK_PV_PRODUCTO
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES A_PRODUCTO(CODIGOBARRAS)
ENABLE;

ALTER TABLE A_PROMOCIONVENDIDOS
ADD CONSTRAINT FK_PV_PROMOCION
    FOREIGN KEY (IDPROMOCION)
    REFERENCES A_PROMOCION(ID)
ENABLE;
-- Creación de la tabla A_PROMOCIONES y especificación de sus restricciones
CREATE TABLE A_PROMOCIONES
(
    IDSUCURSAL NUMBER,
    IDPROMOCION NUMBER,
    CONSTRAINT PROMOCIONES_PK PRIMARY KEY (IDSUCURSAL, IDPROMOCION)
);
ALTER TABLE A_PROMOCIONES
ADD CONSTRAINT FK_PS_SUCURSAL 
    FOREIGN KEY (IDSUCURSAL)
    REFERENCES A_SUCURSAL(ID)
ENABLE;

ALTER TABLE A_PROMOCIONES
ADD CONSTRAINT FK_PS_PROMOCION 
    FOREIGN KEY (IDPROMOCION)
    REFERENCES A_PROMOCION(ID)
ENABLE;
COMMIT;
