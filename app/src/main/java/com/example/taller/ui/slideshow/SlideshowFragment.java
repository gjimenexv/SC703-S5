package com.example.taller.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.taller.R;
import com.example.taller.gestion.EstudianteGestion;
import com.example.taller.model.Estudiante;

public class SlideshowFragment extends Fragment {

    private EditText etId;
    private EditText etNombre;
    private EditText etEdad;

    private Button btInserta;
    private Button btConsulta;
    private Button btModifica;
    private Button btElimina;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        etId = root.findViewById(R.id.etId);
        etNombre = root.findViewById(R.id.etNombre);
        etEdad = root.findViewById(R.id.etEdad);

        btInserta = root.findViewById(R.id.boton1);
        btConsulta = root.findViewById(R.id.boton2);
        btModifica = root.findViewById(R.id.btModifica);
        btElimina = root.findViewById(R.id.btElimina);



        btInserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserta();
            }
        });

        btConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta();
            }
        });

        btModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifica();
            }
        });

        btElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elimina();
            }
        });

        return root;
    }

    //Limpia los contenido de los EditText
    private void limpia() {
        etId.setText("");
        etNombre.setText("");
        etEdad.setText("");
    }

    //Presenta la info de un estudiante a partir de un objeto Estudiante
    private void presenta(Estudiante estudiante) {
        if (estudiante!=null) {
            etId.setText(""+estudiante.getId());
            etNombre.setText(estudiante.getNombre());
            etEdad.setText(""+estudiante.getEdad());
        } else {
            limpia();
        }
    }

    //Toma la info de los editText y crea un objeto Estudiante
    private Estudiante getEstudiante() {
        Estudiante estudiante=null;
        String id=etId.getText().toString();
        String nombre=etNombre.getText().toString();
        String edad=etEdad.getText().toString();
        if (!id.isEmpty() && !nombre.isEmpty() && !edad.isEmpty()) {
            estudiante = new Estudiante(
                    Integer.parseInt(id),
                    nombre,
                    Integer.parseInt(edad));
        } else {
            Toast.makeText(getContext(), "Debe llenar los campos...", Toast.LENGTH_SHORT).show();
        }
        return estudiante;
    }

    private void inserta() {
        if (EstudianteGestion.inserta(getEstudiante())) {
            Toast.makeText(getContext(), "Se insertó el estudiante...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "NO Se insertó el estudiante...", Toast.LENGTH_SHORT).show();
        }
    }

    private void consulta() {
        String id=etId.getText().toString();
        boolean encontrado=false;
        if (!id.isEmpty()) {
            int llave = Integer.parseInt(id);
            Estudiante estudiante = EstudianteGestion.busca(llave);
            if (estudiante!=null) {
                presenta(estudiante);
                encontrado=true;
            }
        }
        if(!encontrado) {
            Toast.makeText(getContext(), "NO Se encontró el estudiante...", Toast.LENGTH_SHORT).show();
        }
    }

    private void modifica() {
        if (EstudianteGestion.actualiza(getEstudiante())) {
            Toast.makeText(getContext(), "Se modificó el estudiante...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "NO se modificó el estudiante...", Toast.LENGTH_SHORT).show();
        }
    }

    private void elimina() {
        String id=etId.getText().toString();
        boolean eliminado=false;
        if (!id.isEmpty()) {
            int llave = Integer.parseInt(id);
            eliminado = EstudianteGestion.elimina(llave);
        }
        if(!eliminado) {
            Toast.makeText(getContext(), "NO Se eliminó el estudiante...", Toast.LENGTH_SHORT).show();
        }
    }
}