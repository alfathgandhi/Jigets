package org.d3ifcool.catat

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activty_show_part.*
import java.lang.StringBuilder

public class AcitivyShowImage : Fragment(){
    private lateinit var databaseReference: DatabaseReference
    var pesan: Array<String>? = null

        companion object {
           lateinit var scaleGestureDetector: ScaleGestureDetector
            var scaleFactor:Float = 1.0f
            lateinit var image : ImageView


        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activty_show_part, container, false)


       pesan = arguments?.getStringArray("Part")
       val tipe = arguments?.getStringArray("namaType")


        Glide.with(this).asBitmap().load(pesan?.get(5)).into(object : SimpleTarget<Bitmap>(){

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
               Log.i("succedY",resource.toString())
                view.findViewById<com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView>(R.id.img_showPart).setImage(
                    ImageSource.bitmap(resource))


            }
        })
          


        view.findViewById<TextView>(R.id.tv_plane_showpart).text = pesan?.get(1)
        view.findViewById<TextView>(R.id.tv_basic_showpart).text = pesan?.get(2)
        view.findViewById<TextView>(R.id.tv_line_showpart).text = pesan?.get(3)




if(!pesan?.get(4).equals("")){
    view.findViewById<TextView>(R.id.tv_keterangan_showpart).visibility= View.VISIBLE
    view.findViewById<TextView>(R.id.keterangan_tv).visibility= View.VISIBLE
    view.findViewById<TextView>(R.id.tv_keterangan_showpart).text = pesan?.get(4)

}


         view.findViewById<TextView>(R.id.uploaded).text ="Uploaded by: " + pesan?.get(6)

        val str  = StringBuilder()

        for (i in tipe!!.indices){

            str.append(tipe.get(i))

            Log.i("uiuiui",tipe[i].toString())

            if(i != tipe.size-1){
                str.append(", ")
            }




        }

        view.findViewById<TextView>(R.id.tv_type_part).text = str.toString()



      //  scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())




//        view.setOnTouchListener(object : View.OnTouchListener{
//            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
//                    return scaleGestureDetector.onTouchEvent(p1)
//            }
//        })

        return view

    }




//    class  ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener(){
//
//        override fun onScale(detector: ScaleGestureDetector?): Boolean {
//
//          scaleFactor *= scaleGestureDetector.scaleFactor
//            image.scaleX = scaleFactor
//            image.scaleY = scaleFactor
//
//            return true
//        }


    }
//}