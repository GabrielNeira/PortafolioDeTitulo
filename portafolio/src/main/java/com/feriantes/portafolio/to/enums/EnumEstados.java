package com.feriantes.portafolio.to.enums;

    public enum EnumEstados {
        CREADO(0),
		INICIADO(1),
        LLENO(2),
        PENDIENTE_TRANPORTISTA(3),
		EN_TRANSPORTE(4),
		TRANSPORTE_COMPLETADO(5),
		FINALIZADO(6),
		CANCELADO(7);

        private int code;

        private EnumEstados(int code) {
        this.code=code;
        }

        public int getCode() {
            return code;
        }   

        public static EnumEstados obtenerEstado(int i){
            for (EnumEstados item : EnumEstados.values()) {
                if(item.getCode()==i)
                    return item;
            }
            return null;
        }
    }
    

