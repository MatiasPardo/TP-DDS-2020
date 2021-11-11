package geSoc.organizacion;

public enum Clasificacion {
	Micro{
		@Override
        public String toString() {
            return "Micro empresa";
        }
	}, Pequenia{
		@Override
        public String toString() {
            return "Pequeña empresa";
        }
	}, MedianaTramo1{
		@Override
        public String toString() {
            return "Mediana empresa tramo 1";
        }
	}, MedianaTramo2{
		@Override
        public String toString() {
            return "Mediana empresa tramo 2";
        }
	}
}
