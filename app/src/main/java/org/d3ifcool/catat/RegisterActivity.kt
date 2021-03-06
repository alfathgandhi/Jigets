package org.d3ifcool.catat

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity:AppCompatActivity() {
    private var mRegister: Button? = null
    private var mRegisNamaUser: EditText? = null
    private var mRegisPass: EditText? = null
    private var mRegisEmail: EditText? = null
    private var auth: FirebaseAuth? = null
    private var mNrpUser: EditText? = null
    private var mNoHP: EditText? = null
    private var mSeksi: EditText? = null
    private var mPassword: EditText? = null
    private var databaseReference: DatabaseReference? = null
    private var id: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mRegister = findViewById(R.id.bt_submit)
        mRegisNamaUser = findViewById(R.id.et_nama_profile)
        mRegisPass = findViewById(R.id.et_password_profile)
        mRegisEmail = findViewById(R.id.et_username_profile)
        mNrpUser = findViewById(R.id.et_nrp_profile)
        mNoHP = findViewById(R.id.et_noHp_profile)
        mSeksi=findViewById(R.id.et_seksi_profile)
        mPassword = findViewById(R.id.et_password_profile)

        auth = FirebaseAuth.getInstance()
        mRegister?.setOnClickListener(View.OnClickListener {
            mRegister?.isEnabled = false

            val dialog = LoadingDialog(this)



            val nama = mRegisNamaUser?.text.toString().toLowerCase().trim()
            val nrp = mNrpUser?.text.toString().trim()
            val hp = mNoHP?.text.toString().trim()
            val seksi = mSeksi?.text.toString().trim()
            val email = mRegisEmail?.text.toString().toLowerCase().trim()
            val pass = mPassword?.text.toString().trim()
            if(TextUtils.isEmpty(nama)|| TextUtils.isEmpty(nrp)|| TextUtils.isEmpty(hp)|| TextUtils.isEmpty(seksi)|| TextUtils.isEmpty(email)|| TextUtils.isEmpty(pass)){
                Toast.makeText(this@RegisterActivity, "Semua kolom harus terisi", Toast.LENGTH_SHORT).show()
            }else {
                auth!!.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this@RegisterActivity) { task ->
                        if (!task.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Gagal Mendaftar, Harap Cek Koneksi Internet\n" + task.exception,
                                Toast.LENGTH_SHORT).show()
                        } else {
                            if (FirebaseAuth.getInstance().currentUser != null) {
                                id = FirebaseAuth.getInstance().currentUser!!.uid

                            }
                            dialog.loadingAlertDialog()
                            databaseReference = FirebaseDatabase.getInstance().getReference("User").child(id!!)
                            val value = User(nama, nrp, seksi,hp , email)

                            databaseReference!!.setValue(value).addOnCompleteListener {
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                Toast.makeText(this@RegisterActivity, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                                finish()

                            }

                        }
                    }
            }
        })
    }
}