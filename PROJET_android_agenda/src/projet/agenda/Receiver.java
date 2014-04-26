package projet.agenda;
  
import android.content.BroadcastReceiver;  
import android.content.Context;  
import android.content.Intent;    
import android.widget.Toast;  
public class Receiver extends BroadcastReceiver {  
    @Override  
    public void onReceive(Context context, Intent intent) {  
      //  String action = intent.getAction();  
      
    //	long alarmtime= intent.getLongExtra("alarm_time", 1);  
        Toast.makeText(context, "Alarm agenda!!!", Toast.LENGTH_SHORT).show();  
    }  
} 
