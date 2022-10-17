package com.example.applicationtry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.applicationtry.databinding.ActivityMainBinding
import com.example.applicationtry.viewmodel.TodoViewModel
//import com.example.applicationtry.viewmodel.TodoViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    val binding = ActivityMainBinding.inflate(layoutInflater)
//    private lateinit var binding: ActivityMainBinding

//    private lateinit var todoAdapter: TodoAdapter
//    private lateinit var todoAdapter: TodoAdapterNew
    private var clicked = false
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        val applicationScope = CoroutineScope(SupervisorJob())
//        val database by lazy { TodoRoomDatabase.getDatabase(this, applicationScope) }
//        val repository by lazy { TodoItemsRepository(database.todoItemDAO()) }


//        MAIN = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        navController = Navigation.findNavController(this, R.id.fragHolder)

        setupActionBarWithNavController(findNavController(R.id.fragHolder))
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.placeHolder, TodoListFragment.newInstance())
//            .commit()

//        binding.fabAdd.apply {
//            setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.placeHolder, AddTodoFragment.newInstance())
//                    .commit()
//            }
//            onFabAddClicked(clicked)
//            clicked = !clicked
//        }

    }


}