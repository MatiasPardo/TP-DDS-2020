
    create table Categoria (
        DTYPE varchar(31) not null,
        id bigint not null,
        montoMaximo int,
        puedeAgregar int,
        primary key (id)
    )

    create table Categoria_EntidadBase (
        Categoria_id bigint not null,
        entidadesBloqueadas_id bigint not null
    )

    create table Compra (
        id bigint ,
        fechaOperacion varbinary(255),
        importe int not null,
        primary key (id)
    )

    create table Direccion (
        id bigint,
        altura varchar(255),
        calle varchar(255),
        ciudad varchar(255),
        codigoPostal varchar(255),
        pais varchar(255),
        piso varchar(255),
        provincia varchar(255),
        primary key (id)
    )

    create table Documento (
        id bigint,
        primary key (id)
    )

    create table EntidadBase (
        id bigint ,
        descripcion varchar(255),
        nombreFicticio varchar(255),
        entidadJuridica_id bigint,
        primary key (id)
    )

    create table EntidadJuridica (
        id bigint,
        CUIT varchar(255),
        defCodeInscIGJ varchar(255),
        direccionPostal varchar(255),
        nombreFicticio varchar(255),
        razonSocial varchar(255),
        tipo integer,
        categoria_id bigint,
        primary key (id)
    )

    create table Etiqueta (
        DTYPE varchar(31) not null,
        id bigint,
        tipo integer,
        operacionDeEgreso_id bigint,
        primary key (id)
    )

    create table Item (
        id bigint,
        cantidad integer not null,
        descripcion varchar(255),
        nombre varchar(255),
        precioUnitario int not null,
        primary key (id)
    )

    create table OperacionDeEgreso (
        id bigint,
        criterioPresupuesto integer,
        descripcion varchar(255),
        fechaOperacion varbinary(255),
        compra_id bigint,
        repositorioDeOperaciones_id bigint,
        entidadJuridica_id bigint,
        primary key (id)
    )

    create table Presupuesto (
        id bigint,
        aceptado int not null,
        documento_id bigint,
        proveedor_id bigint,
        operacionDeEgreso_id bigint,
        primary key (id)
    )

    create table Presupuesto_Item (
        Presupuesto_id bigint not null,
        presupuestoPorItem_id bigint not null
    )

    create table Proveedor (
        id bigint,
        apellido varchar(255),
        direccionPostal varchar(255),
        dni varchar(255),
        nombre varchar(255),
        razonSocial varchar(255),
        primary key (id)
    )

    create table RepositorioDeOperacionesPendientes (
        id bigint,
        primary key (id)
    )

    create table Usuarie (
        id bigint ,
        esAdmin int not null,
        nombre varchar(255),
        passHasheada varchar(255),
        salt varchar(255),
        operacionDeEgreso_id bigint,
        primary key (id)
    )

    create table ValidadorDeOperacion (
        DTYPE varchar(31) not null,
        id bigint not null,
        cantidadDePresupuestosNecesarios integer,
        repositorioDeOperaciones_id bigint,
        primary key (id)
    )

    alter table Categoria_EntidadBase 
        add constraint FK_dpphenrgiieuvcna8xk8pnx5e 
        foreign key (entidadesBloqueadas_id) 
        references EntidadBase

    alter table Categoria_EntidadBase 
        add constraint FK_9jmh9lyx2ikc2ve27wfw67960 
        foreign key (Categoria_id) 
        references Categoria

    alter table EntidadBase 
        add constraint FK_jipg66mf5t7h6ri6acnhne03c 
        foreign key (entidadJuridica_id) 
        references EntidadJuridica

    alter table EntidadJuridica 
        add constraint FK_p9o68hde0t4jbtvw2ayglliuj 
        foreign key (categoria_id) 
        references Categoria

    alter table Etiqueta 
        add constraint FK_80j8jckm58jycudfobop63rsh 
        foreign key (operacionDeEgreso_id) 
        references OperacionDeEgreso

    alter table OperacionDeEgreso 
        add constraint FK_nxgfg9yd84nsjybtok17aqoog 
        foreign key (compra_id) 
        references Compra

    alter table OperacionDeEgreso 
        add constraint FK_awh2hsy75uiw2migle75rr140 
        foreign key (repositorioDeOperaciones_id) 
        references RepositorioDeOperacionesPendientes

    alter table OperacionDeEgreso 
        add constraint FK_1w7km3vstpfblmj5bus2dphef 
        foreign key (entidadJuridica_id) 
        references EntidadJuridica

    alter table Presupuesto 
        add constraint FK_ejlli9oqsua6pdtvfqss7isfd 
        foreign key (documento_id) 
        references Documento

    alter table Presupuesto 
        add constraint FK_rn5b85n7ocgxag5ouwm26i3ig 
        foreign key (proveedor_id) 
        references Proveedor

    alter table Presupuesto 
        add constraint FK_2nlpnbryx3igsveqtr1rpaag1 
        foreign key (operacionDeEgreso_id) 
        references OperacionDeEgreso

    alter table Presupuesto_Item 
        add constraint FK_7f6597lp2x257ws83a95pe73q 
        foreign key (presupuestoPorItem_id) 
        references Item

    alter table Presupuesto_Item 
        add constraint FK_m62atupgrowvuawqbnereasq0 
        foreign key (Presupuesto_id) 
        references Presupuesto

    alter table Usuarie 
        add constraint FK_oxs6sdyn5cettumvaxnac8odj 
        foreign key (operacionDeEgreso_id) 
        references OperacionDeEgreso

    alter table ValidadorDeOperacion 
        add constraint FK_k4h8e69h0aig9x1i8ui1heqpu 
        foreign key (repositorioDeOperaciones_id) 
        references RepositorioDeOperacionesPendientes

    create sequence hibernate_sequence start with 1
