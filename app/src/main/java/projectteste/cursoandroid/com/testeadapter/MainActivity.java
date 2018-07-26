package projectteste.cursoandroid.com.testeadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //change, change. change
    List selections;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///good morning
        selections = new ArrayList();
        count = 0;





        final ListView lista = (ListView) findViewById(R.id.IvEscolas);
        final ArrayList<Escola> escolas = adicionarEscolas();
        final ArrayAdapter adapter = new EscolaAdapter(this, escolas);
        lista.setAdapter(adapter);
        lista.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        lista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked)
            {
                if(checked){
                    selections.add(escolas.get(position));


                    count ++;
                    mode.setTitle(count+" Selected");


                }else{
                    selections.remove(escolas.get(position));
                    count--;
                    mode.setTitle(count+" Selected");
                }

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.my_menu, menu);



                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if(item.getItemId()==R.id.id_delete){
                    for(Object Item : selections){
                        escolas.remove(Item);
                    }
                    adapter.notifyDataSetChanged();
                    mode.finish();
                    return true;
                }







                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                count = 0;
                selections.clear();

            }


        });











    }

    private ArrayList<Escola> adicionarEscolas() {

        ArrayList<Escola> escolas = new ArrayList<Escola>();

        Escola e;
        e = new Escola("Colégio Estadual Atheneu Sergipense","Rua Pacatuba S/N", R.drawable.escola5);
        escolas.add(e);
        e = new Escola("Escola Estadual General Siqueira",
                "Rua Sergipe S/N", R.drawable.escola5);
        escolas.add(e);
        e = new Escola("Escola Tobias Barreto",
                "Av. Otoniel Dórea", R.drawable.escola1);
        escolas.add(e);
        e = new Escola("Colegio Estadual Prof G Rollemberg Leite",
                "R. Franklin de Campos Sobral, 1675", R.drawable.escola2);
        escolas.add(e);
        return escolas;
    }




}
