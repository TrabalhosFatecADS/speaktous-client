package br.com.douglas.speaktous.service;

import android.os.AsyncTask;
import br.com.douglas.speaktous.model.Pessoa;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void,Void, Pessoa> {

    private final String user;
    private final String senha;

    public HTTPService(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }

    @Override
    protected Pessoa doInBackground(Void... voids) {

        StringBuilder resposta = new StringBuilder();

        try {                      //http://192.168.0.17:8080/pessoa/login/?user=Usuario          &pwd=senha
            URL url = new URL("http://192.168.0.17:8080/pessoa/login/?user=" + this.user + "&pwd=" + this.senha );//+ "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()){
                resposta.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        Pessoa pessoa = gson.fromJson(resposta.toString(),Pessoa.class);

        return pessoa;
    }

}
