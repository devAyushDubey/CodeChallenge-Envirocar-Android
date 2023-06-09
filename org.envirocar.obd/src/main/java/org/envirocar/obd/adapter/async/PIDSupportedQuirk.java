/**
 * Copyright (C) 2013 - 2021 the enviroCar community
 *
 * This file is part of the enviroCar app.
 *
 * The enviroCar app is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The enviroCar app is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with the enviroCar app. If not, see http://www.gnu.org/licenses/.
 */
package org.envirocar.obd.adapter.async;

import org.envirocar.obd.adapter.ResponseQuirkWorkaround;

/**
 * Created by matthes on 17.12.15.
 */
public class PIDSupportedQuirk implements ResponseQuirkWorkaround {

    private static final byte[] PREFIX = "B70".getBytes();

    @Override
    public boolean shouldWaitForNextTokenLine(byte[] byteArray) {
        if (byteArray.length > 3) {
            for (int i = 0; i < PREFIX.length; i++) {
                if (byteArray[i] != PREFIX[i]) {
                    return false;
                }
            }

            //it is a PID supported, check the correct length
            return byteArray.length < 14;
        }

        return false;
    }


}
