package ak.core.utilities.global

import ak.core.utilities.global.LogcatUtils.showLogcat
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract

object SystemInteractionsUtils {


    object AppPackageUtils {

        fun openTelegram(context: Context, phoneNumber: String, message: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://t.me/share/url?text=$message&phone=$phoneNumber")

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                // تطبيق Telegram غير مثبت على الجهاز
                // يمكنك إدراج رمز هنا للتعامل مع هذا الحالة على حسب احتياجات التطبيق الخاص بك
            }
        }
    }

    object SMSUtils {


        fun sendSMSToNumber(Context: Context, phoneNumber: String, message: String) {

            try {
                val sms_uri = Uri.parse("smsto:$phoneNumber")
                val sms_intent = Intent(Intent.ACTION_SENDTO, sms_uri)
                sms_intent.putExtra("sms_body", message)
                Context.startActivity(sms_intent)
            } catch (ex: Exception) {

                showLogcat(ex.message.toString())
            }
        }

        fun sendSMS(context : Context,textMessage: String) {

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textMessage)
                type = "text/plain"
            }

// Try to invoke the intent.
            try {
                context.startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                // Define what your app should do if no activity can handle the intent.
            }
        }

    }

    object ClipboardUtils {



         fun copyToClipboard(context: Context,value: String, lable: String = "lable") {
            val   clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            if (value != "" || value.isNotEmpty()) {
                val CopyText: ClipData = ClipData.newPlainText(lable, value)
                clipboardManager.setPrimaryClip(CopyText)
            }
        }

    }

    object ShareUtils {




        fun shareText(context: Context, text: String) {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"

            }
            context.startActivity(
                Intent.createChooser(sendIntent,
                "Share via"))
        }


        //
        val shareApp: (Context, String) -> Unit = { ctx, txt ->
            ctx.startActivity(
                Intent.createChooser(
                    Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, txt)
                        type = "text/plain"
                    },
                    null
                )
            )
        }

        //
        val sharNote: (Context, String, String, then: () -> Unit) -> Unit = { ctx, title, description, then ->
            ctx.startActivity(
                Intent.createChooser(
                    Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "$title\n\n$description")
                        type = "text/plain"
                    },
                    null
                )
            )
            then.invoke()
        }

    }

    object EmailUtils {
        fun sendEmail(context: Context, to: String, subject: String, body: String) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL,
            arrayOf(to))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, body)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }

        val mailTo: (Context, String) -> Unit = { ctx, to ->
            ctx.startActivity(
                Intent.createChooser(
                    Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse(to)
                    },
                    null
                )
            )
        }
    }

    object CallUtils {
        fun makeCall(context: Context, phoneNumber: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            if (intent.resolveActivity(context.packageManager)
            != null) {
                context.startActivity(intent)

            }
        }

        fun makeCallToNumber(context: Context, number: String) {

            val phoneIntent = Intent(
                Intent.ACTION_DIAL, Uri.fromParts(
                    "tel", number, null
                )
            )
            context.startActivity(phoneIntent)
        }
        val callNumber: (Context, String) -> Unit = { ctx, number ->
            ctx.startActivity(
                Intent.createChooser(
                    Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:$number")
                    },
                    null
                )
            )
        }
    }
    object ContactsUtils {
        fun addNewContacts(context: Context, number: String, name: String) {
            try {
                val intent = Intent(Intent.ACTION_INSERT)
                intent.type = ContactsContract.Contacts.CONTENT_TYPE
                intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, number)
                context.startActivity(intent)
            } catch (ex: Exception) {
                //Toast.makeText(Context, "الواتساب الرسمي غير مثبت لديك.", Toast.LENGTH_SHORT).show()

                showLogcat(ex.message.toString())
            }
        }
    }

}