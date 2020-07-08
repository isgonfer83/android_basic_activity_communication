# android_basic_activity_communication
Just a mockup app to demonstrate basic communication between activities, README still in Spanish but all code in English

Las actividades son clases independientes, no comparten variables. Sin embargo, las actividades trabajan de forma coordinada entre ellas para realizar las tareas de la aplicación y para ello deben intercambiar información.

Cuando una actividad lanza otra actividad, puede además enviarle cierta información. Este envío de información se realiza por medio de los extras en los intents.

-> Actividad que lanza otra actividad y le pasa ciertos parámetros:

// Create an intent in order to start a new About activity.
val intent = Intent(this, About::class.java)

// Create some extras which will be available in the launched About activity.
intent.putExtra("name", "John Smith")
intent.putExtra("age", 27)

// Launch new About activity not expecting any data to be returned.
startActivity(intent)

-> En la actividad lanzada podemos recoger los datos que nos ha pasado la actividad lanzadora:

// Get extras provided by launcher activity.
val extras = intent.extras

/* Get each variable provided by launcher activity.
 * extras? => safe call to avoid null pointer exception if extras is null.
 * ?: => Elvis operator: if the first expression exists, returns the value; if it doesn't, returns the value of the second expression.
 */
val name = extras?.getString("name") ?: "no_name"
val age = extras?.getInt("age") ?: -1

La actividad lanzada puede también devolver información a la actividad lanzadora, pero para ello la actividad lanzadora debe utilizar startActivityForResult

-> Lanzando una actividad con intercambio de información esperando que ésta nos devuelva un resultado:

/* Launch new About activity expecting data to be returned.
 * Some id is required to identify the launched activity when data is returned to launcher activity, for example ABOUT_INTENT_ID which is constant defined in a constants file.
*/
startActivityForResult(intent, ABOUT_INTENT_ID)

-> La actividad lanzada recoge los datos enviados por la actividad lanzadora y, además, devuelve un resultado:

// Launched activity can also return data to launcher activity.
val intent = Intent()
intent.putExtra("greeting", "Hello " + name + "!")
setResult(Activity.RESULT_OK, intent)
finish()

-> En la actividad lanzada también podemos indicar otro tipo de resultado para que la actividad lanzadora lo maneje:

val intent = Intent()
setResult(Activity.RESULT_CANCEL, intent)
finish()

->	La actividad lanzadora recoge el resultado de la actividad lanzada de la siguiente manera. Solo se puede implementar un único método onActivityResult y una actividad puede lanzar muchas otras actividades por eso necesitamos identificar cada actividad lanzada con un código.

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == ABOUT_INTENT_ID && resultCode == Activity.RESULT_OK) {
        val greeting = data?.extras?.getString("greeting") ?: "error"
        Toast.makeText(this, greeting, Toast.LENGTH_SHORT).show()
    }
}

-> ¿Dónde definir constantes? En este ejemplo se ha definido el fichero Constants.kt donde se ha definido la constante utilizada de la siguiente manera:

package com.example.basicactivitycommunication
const val ABOUT_INTENT_ID = 1
