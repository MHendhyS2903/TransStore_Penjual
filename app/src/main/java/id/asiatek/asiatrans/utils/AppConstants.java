/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package id.asiatek.asiatrans.utils;

/**
 * Created by amitshekhar on 07/07/17.
 */

public final class AppConstants {

    public static final String KEY_REQUEST = "KEY_REQUEST";
    public static final String KEY_REQUESTS = "KEY_REQUESTS";
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    //public static final String BASE_URL = "https://dev.asiatrans.id";
    public static final String BASE_URL = "https://atapi-store-run.conveyor.cloud";
    public static final String UploadURL = BASE_URL + "/public/uploads/";

    /*--API--*/
    public static final String PostLoginHP= BASE_URL + "/Store/Login";
    public static final String PostUpdateAccount = BASE_URL + "/Store/Update";
    public static final String PostRegister = BASE_URL + "/Store/Registration";
    public static final String AddEtalase = BASE_URL + "/Etalase/Add";
    public static final String getUnpaidTrans = BASE_URL + "/Order/GetByUnpaidTransaction";
    public static final String getProduct = BASE_URL + "/Item/GetByStore";
    public static final String AddProduct = BASE_URL + "/Item/Add";
    public static final String getCartList = BASE_URL + "/Item/Update";
    public static final String UpdateProduct = BASE_URL + "/Item/Update";
    public static final String AddOutlet= BASE_URL + "/restaurantoutlet/add";
    public static final String UpdateOutlet = BASE_URL + "/restaurantoutlet/update";
    public static final String DeleteOutlet = BASE_URL + "/restaurantoutlet/delete";
    public static final String UpdateProfile = BASE_URL + "/restaurant/updateprofile";
    public static final String GetProfile = BASE_URL + "/Store/getbytoken";
    public static final String GetEtalaseList = BASE_URL + "/Etalase/GetByStore";
    public static final String AcceptOrder = BASE_URL + "/restaurant/setactive";
    public static final String FinishOrder = BASE_URL + "/restaurant/setfinish";
    public static final String GetArea = BASE_URL + "/area/get";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
