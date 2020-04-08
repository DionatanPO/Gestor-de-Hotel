package Model.dao;

@FunctionalInterface
public interface DataInterface extends Runnable{

    public static String dataRecebidaMysql(String data) {
        String dia = data.substring(8, 10);
        String mes = data.substring(5, 7);
        String ano = data.substring(0, 4);
        data = dia + "/" + mes + "/" + ano;
        return data;
    }

}
