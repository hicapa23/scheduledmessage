package de.vpsservice.scheduledmessage.util;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

public class ContactPickerHelper {

    public static final int CONTACT_PICK_REQUEST = 1001;

    public static void launchPicker(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        activity.startActivityForResult(intent, CONTACT_PICK_REQUEST);
    }

    public static String extractPhoneNumber(Activity activity, Intent data) {
        Uri contactUri = data.getData();
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = activity.getContentResolver().query(contactUri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String rawNumber = cursor.getString(numberIndex);
            cursor.close();

            // Limpia el número: elimina espacios, guiones, paréntesis
            String cleanNumber = rawNumber.replaceAll("[^\\d]", "");
            return cleanNumber;
        }

        Toast.makeText(activity, "No se pudo obtener el número", Toast.LENGTH_SHORT).show();
        return null;
    }


}
