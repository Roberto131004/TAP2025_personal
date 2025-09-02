package Views;

import Controller.CajeroController;
import Models.CajeroModels;

public class CajeroAutomatico {
    public static void main(String[] args) {

        CajeroModels model= new CajeroModels();
        CajeroView view= new CajeroView();
        CajeroController controller= new CajeroController(model, view);
        controller.iniciarSistema();


    }
}
