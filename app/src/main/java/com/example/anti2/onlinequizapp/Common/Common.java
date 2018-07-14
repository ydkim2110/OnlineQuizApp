package com.example.anti2.onlinequizapp.Common;

import com.example.anti2.onlinequizapp.Model.Question;
import com.example.anti2.onlinequizapp.Model.User;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static String categoryId;
    public static String categoryName;
    public static User currentUser;
    public static List<Question> questionList = new ArrayList<>();

    public static final String STR_PUSH = "pushNotification";
}
