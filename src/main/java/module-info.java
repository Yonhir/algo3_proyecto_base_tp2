module edu.fiuba.algo3 {
    requires json.simple;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.vistas;
    exports edu.fiuba.algo3.modelo;
    opens edu.fiuba.algo3.modelo;
}