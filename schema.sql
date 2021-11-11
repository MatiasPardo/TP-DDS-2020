
    create table Categoria (
        DTYPE varchar(31) not null,
        id bigint generated by default as identity (start with 1),
        montoMaximo double,
        nombre varchar(255),
        puedeAgregar boolean,
        primary key (id)
    )

    create table Compra (
        id bigint generated by default as identity (start with 1),
        fechaOperacion varbinary(255),
        importe double not null,
        medioDePago_id bigint,
        primary key (id)
    )

    create table Direccion (
        id bigint generated by default as identity (start with 1),
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
        id bigint generated by default as identity (start with 1),
        primary key (id)
    )

    create table EntidadBase (
        id bigint generated by default as identity (start with 1),
        descripcion varchar(255),
        nombreFicticio varchar(255),
        entidadJuridica_id bigint,
        primary key (id)
    )

    create table EntidadJuridica (
        id bigint generated by default as identity (start with 1),
        CUIT varchar(255),
        defCodeInscIGJ varchar(255),
        direccionPostal varchar(255),
        nombreFicticio varchar(255),
        razonSocial varchar(255),
        tipo integer,
        categoria_id bigint,
        primary key (id)
    )

    create table EntidadesBloqueadas (
        Categoria_id bigint not null,
        entidadesBloqueadas_id bigint not null
    )

    create table Etiqueta (
        DTYPE varchar(31) not null,
        id bigint generated by default as identity (start with 1),
        tipo integer,
        proveedor_id bigint,
        operacionDeEgreso_id bigint,
        primary key (id)
    )

    create table Item (
        id bigint generated by default as identity (start with 1),
        cantidad integer not null,
        descripcion varchar(255),
        nombre varchar(255),
        precioUnitario double not null,
        presupuesto_id bigint,
        primary key (id)
    )

    create table MedioDePago (
        id bigint generated by default as identity (start with 1),
        IdentificadorPago varchar(255),
        administradora integer,
        tipoMedioPago integer,
        primary key (id)
    )

    create table NotificacionDeOperacionDeEgreso (
        id bigint generated by default as identity (start with 1),
        esValida boolean not null,
        operacionDeEgreso_id bigint,
        primary key (id)
    )

    create table NotificacionDeOperacionDeEgreso_resultados (
        NotificacionDeOperacionDeEgreso_id bigint not null,
        resultados varchar(255)
    )

    create table OperacionDeEgreso (
        id bigint generated by default as identity (start with 1),
        criterioPresupuesto integer,
        descripcion varchar(255),
        fechaOperacion date,
        compra_id bigint,
        entidadJuridica_id bigint,
        programacion_id bigint,
        primary key (id)
    )

    create table Periodo (
        id bigint generated by default as identity (start with 1),
        cantidadRepeticiones integer not null,
        fechaFin timestamp,
        fechaInicio timestamp,
        intervaloRepeticion integer not null,
        primary key (id)
    )

    create table Presupuesto (
        id bigint generated by default as identity (start with 1),
        aceptado boolean not null,
        documento_id bigint,
        proveedor_id bigint,
        operacionDeEgreso_id bigint,
        primary key (id)
    )

    create table Programacion (
        id bigint generated by default as identity (start with 1),
        estaActiva boolean not null,
        identificador varchar(255),
        periodo_id bigint,
        primary key (id)
    )

    create table Proveedor (
        id bigint generated by default as identity (start with 1),
        apellido varchar(255),
        dni varchar(255),
        nombre varchar(255),
        razonSocial varchar(255),
        direccionPostal_id bigint,
        primary key (id)
    )

    create table Usuarie (
        id bigint generated by default as identity (start with 1),
        esAdmin boolean not null,
        nombre varchar(255),
        passHasheada varchar(255),
        salt varchar(255),
        entidad_id bigint,
        operacionDeEgreso_id bigint,
        primary key (id)
    )

    create table Usuarie_NotificacionDeOperacionDeEgreso (
        Usuarie_id bigint not null,
        bandejaDeMensajes_id bigint not null
    )

    alter table Usuarie_NotificacionDeOperacionDeEgreso 
        add constraint UK_abky8re74c2qvgs14y6i1agfw  unique (bandejaDeMensajes_id)

    alter table Compra 
        add constraint FK_sye278mbbtq5wex2w4fbyms2w 
        foreign key (medioDePago_id) 
        references MedioDePago

    alter table EntidadBase 
        add constraint FK_jipg66mf5t7h6ri6acnhne03c 
        foreign key (entidadJuridica_id) 
        references EntidadJuridica

    alter table EntidadJuridica 
        add constraint FK_p9o68hde0t4jbtvw2ayglliuj 
        foreign key (categoria_id) 
        references Categoria

    alter table EntidadesBloqueadas 
        add constraint FK_3csyy309xaejtgfknwf7761je 
        foreign key (entidadesBloqueadas_id) 
        references EntidadBase

    alter table EntidadesBloqueadas 
        add constraint FK_2dr14o18jo4c71uio3em7loyb 
        foreign key (Categoria_id) 
        references Categoria

    alter table Etiqueta 
        add constraint FK_s84t9wdtdlj10hnpada16thfk 
        foreign key (proveedor_id) 
        references Proveedor

    alter table Etiqueta 
        add constraint FK_80j8jckm58jycudfobop63rsh 
        foreign key (operacionDeEgreso_id) 
        references OperacionDeEgreso

    alter table Item 
        add constraint FK_kaob51dneofur9wsdjswwopwp 
        foreign key (presupuesto_id) 
        references Presupuesto

    alter table NotificacionDeOperacionDeEgreso 
        add constraint FK_8trex59ywfax0151ud9yhh593 
        foreign key (operacionDeEgreso_id) 
        references OperacionDeEgreso

    alter table NotificacionDeOperacionDeEgreso_resultados 
        add constraint FK_e7sqy87e4u30vbv8yi0heb8ct 
        foreign key (NotificacionDeOperacionDeEgreso_id) 
        references NotificacionDeOperacionDeEgreso

    alter table OperacionDeEgreso 
        add constraint FK_nxgfg9yd84nsjybtok17aqoog 
        foreign key (compra_id) 
        references Compra

    alter table OperacionDeEgreso 
        add constraint FK_1w7km3vstpfblmj5bus2dphef 
        foreign key (entidadJuridica_id) 
        references EntidadJuridica

    alter table OperacionDeEgreso 
        add constraint FK_auup3s6k3dymw1utcbrju0s39 
        foreign key (programacion_id) 
        references Programacion

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

    alter table Programacion 
        add constraint FK_ec3y8117ssciky1gfojx14cpf 
        foreign key (periodo_id) 
        references Periodo

    alter table Proveedor 
        add constraint FK_hihr4t56yey6ytenl7rvao2hx 
        foreign key (direccionPostal_id) 
        references Direccion

    alter table Usuarie 
        add constraint FK_a6ib8ain2qeoonu9tiqidhibc 
        foreign key (entidad_id) 
        references EntidadJuridica

    alter table Usuarie 
        add constraint FK_oxs6sdyn5cettumvaxnac8odj 
        foreign key (operacionDeEgreso_id) 
        references OperacionDeEgreso

    alter table Usuarie_NotificacionDeOperacionDeEgreso 
        add constraint FK_abky8re74c2qvgs14y6i1agfw 
        foreign key (bandejaDeMensajes_id) 
        references NotificacionDeOperacionDeEgreso

    alter table Usuarie_NotificacionDeOperacionDeEgreso 
        add constraint FK_f09nyyuxrleuosc76ua380e8u 
        foreign key (Usuarie_id) 
        references Usuarie
