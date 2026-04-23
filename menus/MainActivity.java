package com.example.menus;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup Toolbar for Options Menu
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup Context Menu
        TextView tvContextMenu = findViewById(R.id.tv_context_menu);
        registerForContextMenu(tvContextMenu);

        // Setup Popup Menu
        Button btnPopupMenu = findViewById(R.id.btn_popup_menu);
        btnPopupMenu.setOnClickListener(v -> showPopupMenu(v));
    }

    // --- 1. OPTIONS MENU & SUBMENU ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_search) {
            Toast.makeText(this, "Options Menu: Search clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_profile) {
            Toast.makeText(this, "Options Menu: Profile clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_account) {
            Toast.makeText(this, "SubMenu: Account Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_privacy) {
            Toast.makeText(this, "SubMenu: Privacy Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    // --- 2. CONTEXT MENU ---
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select an action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.context_edit) {
            Toast.makeText(this, "Context Menu: Edit clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.context_share) {
            Toast.makeText(this, "Context Menu: Share clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.context_delete) {
            Toast.makeText(this, "Context Menu: Delete clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return super.onContextItemSelected(item);
    }

    // --- 3. POPUP MENU ---
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        
        popupMenu.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            
            if (id == R.id.popup_reply) {
                Toast.makeText(this, "Popup Menu: Reply clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.popup_forward) {
                Toast.makeText(this, "Popup Menu: Forward clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.popup_mark_read) {
                Toast.makeText(this, "Popup Menu: Mark as Read clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            
            return false;
        });
        
        popupMenu.show();
    }
}