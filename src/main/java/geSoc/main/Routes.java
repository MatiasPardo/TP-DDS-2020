package geSoc.main;

import geSoc.calendarizacion.*;
import geSoc.controllers.*;
import spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Routes {

    public static void main(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);
        Spark.staticFileLocation("/public");
        //Spark.before(filter);
        new Inicializar().init();
		InitializerQuartz.getInstance().init();

        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        HomeController homeController = new HomeController();
        UsuariosController usuariosController = new UsuariosController();
        OpEgresoController opEgresoController = new OpEgresoController();
        EntidadController entidadController = new EntidadController();
        MensajeController mensajeController = new MensajeController();
        Spark.get("/", (request, response) -> homeController.getHome(request), engine);

        Spark.get("/login", usuariosController::getLoginForm, engine);
        Spark.post("/login", (request, response) -> usuariosController.iniciarSesion(request, response));
        Spark.get("/cerrarSesion", (request, response) -> usuariosController.cerrarSesion(request), engine);

        Spark.get("/opEgreso", (request, response) -> opEgresoController.getOpEgreso(request), engine);
        Spark.post("/opEgreso", (request, response) -> opEgresoController.crearOpEgreso(request,response));   
 
        Spark.get("/mensajes", (request, response) -> mensajeController.getMensajes(request,response), engine);
        
        Spark.post("/crearEntidadJuridica", (request, response) -> entidadController.crearEntidadJuridica(request, response), engine);
        
        Spark.post("/crearEntidadBase", (request, response) -> entidadController.crearEntidadBase(request, response), engine);
        Spark.get("/crearEntidad", (request, response) -> entidadController.getEntidad(request), engine);
       
        Spark.get("/categorias", (request, response) -> entidadController.getCategorias(request), engine);
        Spark.get("/categorias/entidades", (request, response) -> entidadController.getEntidadesPorCategoria(request),engine);
    //  Spark.get("/entidades", (request, response) -> entidadController.getEntidades(request), engine);
    //  Spark.get("/entidadesXcat", (request, response) -> entidadController.getEntidadesCategoria(request),engine);
    //  Spark.get("/entidades/:id", (request, response) -> entidadController.getDetalleEntidades(request, response, engine));
        
    }

}
