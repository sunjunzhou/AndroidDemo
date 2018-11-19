package e.sunjunzhou.androidapplist;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrowseAppListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listview=null;
    private List<AppInfo>mlistAppInfo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_app_list);
        listview=(ListView)findViewById(R.id.listviewApp);
        mlistAppInfo=new ArrayList<AppInfo>();
        queryAppInfo();
        BrowseApplicationInfoAdapter browseAppAdapter=new BrowseAPPlicationInfoAdapter(this,mlistAppInfo);
        listview.setAdapter(browseAppAdapter);
        listview.setOnItemClickListener(this);

    }
    public void onItemClick(AdapterView<?>agr0, View view,int position,long arg3){
        Intent intent=mlistAppInfo.get(position).getIntent();
        startActivity(intent);
    }
    public void queryAppInfo(){
        PackageManager pm=this.getPackageManager();
        Intent mainIntent=new Intent(Intent.ACTION_MAIN,null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo>resolveInfos=pm.queryIntentActivities(mainIntent,PackageManager.MATCH_DEFAULT_ONLY);
        Collections.sort(resolveInfos,new ResolveInfo.DisplayNameComparator(pm));
        if(mlistAppInfo!=null){
            mlistAppInfo.clear();
            for (ResolveInfo info:resolveInfos){
                String activityName=reInfo.activityInfo.name;
                String pkgName=reInfo.activityInfo.packageName;
                String appLabel=(String)reInfo.loadLabel(pm);
                Drawable icon=reInfo.loadIcon(pm);
                Intent intent=new Intent();
                intent.setComponent(new ComponentName(pkgName,activityName));
                AppInfo appInfo=new AppInfo();
                appInfo.setAppInfo(appLabel+activityName);
                appInfo.setPkgName(pkgName);
                appInfo.setAppIcon(icon);
                appInfo.setIntent(launchIntent);
                mlistAppInfo.add(appInfo);
                System.out.println(appLabel+"activityName---"+activityName+"pkgName---"+pkgName);
            }
        }

        }
    }
}
