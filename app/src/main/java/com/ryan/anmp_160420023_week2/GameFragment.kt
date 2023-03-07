package com.ryan.anmp_160420023_week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.setText("$playerName's Turn")
        }

        var point = 0

//        var x = Random.nextInt(0, 100)
//        var y = Random.nextInt(0, 100)
        var x = (0..30).shuffled().last()
        var y = (0..30).shuffled().last()

        txtQuestion.setText(x.toString() + " + " + y.toString())

        btnSubmit.setOnClickListener {
            val answer = txtAnswer.text.toString().toInt()
            if(answer == (x + y)){
                point += 1
                x = (0..30).shuffled().last()
                y = (0..30).shuffled().last()
                txtQuestion.setText(x.toString() + " + " + y.toString())
                txtAnswer.setText("")
            }
            else{
                val action = GameFragmentDirections.actionResultFragment(point)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}