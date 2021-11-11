package geSoc.compra;

public enum TipoMedioPago {
	TarjetaCredito, TarjetaDebito, Efectivo, ATM
}

/*
		TarjetaCredito{

		}, TarjetaDebito{
			@Override
			public boolean validar(Administradora administradora) {
				ArrayList<Administradora> admins = new ArrayList<Administradora>();
				admins.add(Administradora.MasterCard);
				admins.add(Administradora.Visa);
				return administradoraValida(administradora, admins);
			}
		}, Efectivo{
			@Override
			public boolean validar(Administradora administradora) {
				ArrayList<Administradora> admins = new ArrayList<Administradora>();
				admins.add(Administradora.PagoFacil);
				admins.add(Administradora.RapiPago);
				return administradoraValida(administradora, admins);
			}
		}, ATM{
			@Override
			public boolean validar(Administradora administradora) {
				ArrayList<Administradora> admins = new ArrayList<Administradora>();
				admins.add(Administradora.RedLink);
				return administradoraValida(administradora, admins);
			}
		};

		//private static ArrayList<Administradora> admins = new ArrayList<Administradora>();

		public boolean validar(Administradora administradora) {
			return false;
		}
		private static boolean administradoraValida(Administradora administradora, ArrayList<Administradora> admins) {
			return admins.stream().filter(admin -> admin == administradora).count() > 0;
		}


}

*/
