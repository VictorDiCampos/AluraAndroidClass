package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by victorcampos on 22/03/18.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY," +
                " nome TEXT NOT NULL," +
                " endereco TEXT," +
                " telefone TEXT," +
                " site TEXT," +
                " nota REAL," +
                " foto TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Alunos ADD COLUMN foto TEXT;";
                db.execSQL(sql);

                // Um case para cada versão nova, sem break, pois desta forma
                // será desde a ultima versão que o usuário tenha até a última liberada

                //case 2:
                //    sql = "ALTER TABLE Alunos ADD COLUMN cpf TEXT;";
                //    db.execSQL(sql);
        }

//        String sql = "DROP TABLE IF EXISTS Alunos;";
//        onCreate(db);
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getAlunoContentValues(aluno);

        db.insert("Alunos", null, dados);
    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM Alunos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();
        while (c.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setFoto(c.getString(c.getColumnIndex("foto")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db =  getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        db.delete("Alunos", "id = ?", params);
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        ContentValues dados = getAlunoContentValues(aluno);

        db.update("Alunos", dados, "id = ?", params);
    }

    private ContentValues getAlunoContentValues(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        dados.put("foto", aluno.getFoto());

        return dados;
    }
}
