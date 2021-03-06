/*
 * Copyright (C) 2019 Jorrit "Chainfire" Jongma
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package eu.chainfire.holeylight.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import eu.chainfire.holeylight.BuildConfig;
import eu.chainfire.holeylight.R;

public class TestNotification {
    static final int NOTIFICATION_ID_MAIN = 1001;
    static final int NOTIFICATION_ID_TUNE = 1002;
    static final int NOTIFICATION_ID_COLOR = 1003;

    static void show(Context context, int id) {
        NotificationManagerCompat.from(context).deleteNotificationChannel(BuildConfig.APPLICATION_ID + ":test");
        final NotificationChannel chan = new NotificationChannel(BuildConfig.APPLICATION_ID + ":test", context.getString(R.string.app_name), NotificationManager.IMPORTANCE_LOW);
        chan.setDescription(context.getString(R.string.app_name));
        chan.enableLights(true);
        chan.setLightColor(Color.WHITE);
        NotificationManagerCompat.from(context).createNotificationChannel(chan);

        Notification notificationTest = (new NotificationCompat.Builder(context, chan.getId()))
                .setContentTitle(context.getString(R.string.notification_test_title))
                .setContentText(context.getString(R.string.notification_test_description))
                .setBadgeIconType(NotificationCompat.BADGE_ICON_NONE)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setNumber(0)
                .setSmallIcon(R.drawable.ic_launcher_vector)
                .build();

        NotificationManagerCompat.from(context).notify(id, notificationTest);
    }

    static void hide(Context context, int id) {
        NotificationManagerCompat.from(context).cancel(id);
    }
}
