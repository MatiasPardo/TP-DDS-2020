package geSoc.validadores;

public enum ResultadoValidacion {

	CantidadPresupuestoInvalido{
		@Override
		public String toString(){
			return "No cumple Cantidad De Presupuestos";
		}
	},
	CantidadPresupuestoValido{
		@Override
		public String toString(){
			return "Cumple Cantidad De Presupuestos";
		}
	},
	CriterioSeleccionValido{
		@Override
		public String toString(){
			return "Cumple Criterio de Seleccion";
		}
	},
	CriterioSeleccionNoIndicado{
		@Override
		public String toString(){
			return "No se encuentra seteado el criterio de seleccion";
		}
	}, NoHayCompra{
		@Override
		public String toString(){
			return "La operacion de egreso todavia no tiene una compra realizada";
		}
	}, CompradoConPresupuestoPropio{
		@Override
		public String toString(){
			return "La compra fue realizada en base a uno de sus presupuestos";
		}
	}, CompradoConUnPresupuestoNoPropio{
		@Override
		public String toString(){
			return "La compra no fue realiza en base a uno de sus presupuestos";
		}
	}, NoCuentaConPresupuestos{
		@Override
		public String toString(){
			return "La operacion no cuenta con presupesto";
		}
	}, CriterioSeleccionInvalido{
		@Override
		public String toString(){
			return "El presupuesto elegido no cumple con el criterio de seleccion";
		}
	}
}
