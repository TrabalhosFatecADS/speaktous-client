package br.com.douglas.speaktous.service;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import br.com.douglas.speaktous.model.Pessoa;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PessoaService extends AsyncTask<Void,Void, Pessoa> {

    private final Long id;
    private final String nome;
    private final String user;
    private final String passwd;
    private final String email;
    private final String dtcadastro;
    private final int operacao;

    public PessoaService(Long id, String nome, String user, String passwd, String email, String dtcadastro, int operacao) {
        this.id = id;
        this.nome = nome;
        this.user = user;
        this.passwd = passwd;
        this.email = email;
        this.dtcadastro = dtcadastro;
        this.operacao = operacao;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Pessoa doInBackground(Void... voids) {
        Pessoa pessoa = new Pessoa();
        if (operacao == 1) {
            //pessoa.setNome(nome);
            //pessoa.setUser(user);
            pessoa.setPasswd(passwd);
            pessoa.setEmail(email);
            pessoa.setDtcadastro(dtcadastro);
            try {
                sendPost(pessoa);
            } catch (MinhaException e) {
                e.printStackTrace();
            }
        }
        return pessoa;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int sendPost(Pessoa pessoa) throws MinhaException {

        try {
            // Cria um objeto HttpURLConnection:
            HttpURLConnection request = (HttpURLConnection) new URL("http://192.168.0.17:8080/pessoa/").openConnection();

            try {
                // Define que a conexão pode enviar informações e obtê-las de volta:
                request.setDoOutput(true);
                request.setDoInput(true);

                // Define o content-type:
                request.setRequestProperty("Content-Type", "application/json");

                // Define o método da requisição:
                request.setRequestMethod("POST");

                // Conecta na URL:
                request.connect();

                Gson gson = new Gson();
                // Escreve o objeto JSON usando o OutputStream da requisição:
                try (OutputStream outputStream = request.getOutputStream()) {
                    outputStream.write(gson.toJson(pessoa, Pessoa.class).getBytes("UTF-8"));
                }

                // Retorno da operação 200 sucesso
                return request.getResponseCode();
            } finally {
                request.disconnect();
            }
        } catch (IOException ex) {
            throw new MinhaException(ex);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readResponse(HttpURLConnection request) throws IOException {
        ByteArrayOutputStream os;
        try (InputStream is = request.getInputStream()) {
            os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
        return new String(os.toByteArray());
    }

    public static class MinhaException extends Exception {
        private static final long serialVersionUID = 1L;

        public MinhaException(Throwable cause) {
            super(cause);
        }
    }
}