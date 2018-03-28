package br.com.alura.agenda;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by victorcampos on 20/03/18.
 */

public class FormularioHelper  {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;
    private final ImageView campoFoto;

    private Aluno aluno;

    public FormularioHelper(Activity formulario_activity) {
        campoNome = (EditText) formulario_activity.findViewById(R.id.form_nome);
        campoEndereco = (EditText) formulario_activity.findViewById(R.id.form_endereco);
        campoTelefone = (EditText) formulario_activity.findViewById(R.id.form_telefone);
        campoSite = (EditText) formulario_activity.findViewById(R.id.form_site);
        campoNota = (RatingBar) formulario_activity.findViewById(R.id.form_nota);
        campoFoto = (ImageView) formulario_activity.findViewById(R.id.form_foto);
        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        aluno.setFoto((String) campoFoto.getTag());

        return aluno;
    }

    public void setFormData(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress(aluno.getNota().intValue());
        carregaImagem(aluno.getFoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapLowQ = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapLowQ);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}
