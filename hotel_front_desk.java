package Hotel_management_system;

import java.awt.*;
import java.util.Scanner;

/**
 * @author wolf
 * @date 02/11/2021
 *
 * 作用：酒店管理系统的前台。也是酒店管理系统的入口
 * */
public class hotel_front_desk {
    public static void main(String[] args) {
        //登录所需变量
        boolean isline = true;         //是否在线，登录成功后上线，即isline赋值为true
        Login login = new Login();      //登录类，用于登录相关的操作
        String adminID;                 //管理员ID

        //登录循环
        while(!isline) {
            System.out.println("\n\n\n\n\n\n\n\n");
            System.out.println("===========================================================");
            System.out.println("                         登录页面                           ");
            System.out.println("-----------------------------------------------------------");
            System.out.print("1. 登录\n" +
                    "2. 注册\n" +
                    "3. 修改密码\n" +
                    "4. 注销账户\n" +
                    "0. 退出系统\n");
            System.out.println("===========================================================");
            System.out.print("请选择功能：");
            switch (new Scanner(System.in).nextInt()){
                //登录
                case 1:{
                    int num;                //账户号
                    String password;        //账户密码
                    System.out.println("***************************");
                    System.out.print(  "请输入账户号(纯数字)：");
                    num = new Scanner(System.in).nextInt();
                    System.out.print(  "请输入账户密码：");
                    password = new Scanner(System.in).nextLine();
                    System.out.println("***************************");
                    adminID=login.signIn(num,password);
                    if(adminID == null)
                        System.out.println("登录失败！");
                    else {
                        System.out.println("登录成功！");
                        isline = true;
                    }
                    break;
                }
                //注册
                case 2:{
                    String ID;
                    int num;
                    String password;
                    System.out.println("***************************");
                    System.out.print("请输入ID(用户名):");
                    ID = new Scanner(System.in).nextLine();
                    System.out.print("请输入账户号(纯数字)：");
                    while(login.isRepetition(num = new Scanner(System.in).nextInt())){
                        System.out.print("该账户号已存在，请重新输入：");
                    }
                    password = vaildatePassword();
                    System.out.println("***************************");
                    if(login.signUp(ID,num,password))
                        System.out.println("注册成功！");
                    break;
                }
                //修改密码
                case 3:{
                    int num;
                    String newPassword;
                    String oldPassword;
                    System.out.println("***************************");
                    System.out.print("请输入账户号(纯数字)：");
                    num = new Scanner(System.in).nextInt();
                    if(!login.isRepetition(num)) {                       //验证是否存在该账户
                        System.out.println("不存在该账户！");
                        break;
                    }
                    System.out.print("请输入旧密码：");
                    oldPassword = new Scanner(System.in).nextLine();
                    newPassword = vaildatePassword();
                    System.out.println("***************************");
                    if(login.modifiPassword(num,oldPassword,newPassword)){
                        System.out.println("密码修改成功！");
                    }
                    else{
                        System.out.println("密码修改失败！");
                    }
                    break;
                }
                //注销账户
                case 4:{
                    int num;
                    String password;
                    System.out.println("***************************");
                    System.out.print("请输入账户号(纯数字)：");
                    num = new Scanner(System.in).nextInt();
                    if(!login.isRepetition(num)){
                        System.out.println("账户不存在！");
                        break;
                    }
                    password = vaildatePassword();
                    if(login.logout(num,password)){
                        System.out.println("已注销账户！");
                    }
                    else{
                        System.out.println("注销账户失败！");
                    }
                    break;
                }
                case 0:
                    System.exit(0);
                default:
                    break;
            }
        }
/*        rooms = new Room[STOREY+1][ROOM+1];             //初始化----
        initRooms(rooms,STOREY,ROOM);                   //-----酒店房间*/
        Hotel hotel = new Hotel();
        while (isline) {
            System.out.println("\n\n\n\n\n\n\n\n");
            System.out.println("===========================================================");
            System.out.println("                         功能主页                           ");
            System.out.println("-----------------------------------------------------------");
            System.out.print("1. 预订房间\n" +
                    "2. 登记入住\n" +
                    "3. 退房\n" +
                    "4. 查看所有房间状态\n" +
                    "5. 查找房间状态\n" +
                    "0. 退出系统\n");
            System.out.println("===========================================================");
            System.out.print("请选择功能：");
            switch (new Scanner(System.in).nextInt()){
                //预订房间
                case 1:{
                    System.out.print("请输入想要预订的房间号：");
                    int num = new Scanner(System.in).nextInt();
                    if(!hotel.isExistRoom(num)){
                        System.out.println("无该房间！");
                        break;
                    }
                    if(hotel.getConditionNum(num) == 0){
                        System.out.print("输入 '1' 确定，'0' 取消：");
                        if(new Scanner(System.in).nextInt() == 1){
                            hotel.setCondition(num,1);
                        }
                    }else{
                        System.out.println("该房间已经被占用，预订失败!");
                    }
                    System.out.println("预订成功！");
                    System.out.println("\n任意键继续！");
                    new Scanner(System.in).nextLine();
                    break;
                }
                //登记入住
                case 2:{
                    System.out.print("是否有预订？'1' 有 '0' 没有：");
                    if(new Scanner(System.in).nextInt() == 1){
                        System.out.print("请输入要入住的房间号：");
                        int num = new Scanner(System.in).nextInt();
                        if(!hotel.isExistRoom(num)){
                            System.out.println("无该房间！");
                            break;
                        }
                        if(hotel.getConditionNum(num) == 0 || hotel.getConditionNum(num) == 1){
                            System.out.print("输入 '1' 确定，'0' 取消：");
                            if(new Scanner(System.in).nextInt() == 1){
                                hotel.setCondition(num,2);
                            }
                        }else{
                            System.out.println("该房间已经被占用，入住失败!");
                        }
                    }
                    else{
                        System.out.print("请输入入住房间：");
                        int num = new Scanner(System.in).nextInt();
                        if(!hotel.isExistRoom(num)){
                            System.out.println("无该房间！");
                            break;
                        }
                        if(hotel.getConditionNum(num) == 0){
                            System.out.print("输入 '1' 确定，'0' 取消：");
                            if(new Scanner(System.in).nextInt() == 1){
                                hotel.setCondition(num,2);
                            }
                        }else{
                            System.out.println("该房间已经被占用，入住失败!");
                        }
                    }
                    System.out.println("成功入住！");
                    System.out.println("\n任意键继续！");
                    new Scanner(System.in).nextLine();
                    break;
                }
                //退房
                case 3:{
                    System.out.print("请输入退房房间号：");
                    int num = new Scanner(System.in).nextInt();
                    if(!hotel.isExistRoom(num)){
                        System.out.println("无该房间！");
                        break;
                    }
                    if(hotel.getConditionNum(num) != 0){
                        System.out.print("输入 '1' 确定，'0' 取消：");
                        if(new Scanner(System.in).nextInt() == 1){
                            hotel.setCondition(num,0);
                        }
                    }else{
                        System.out.println("该房间已空置，退房失败!");
                    }
                    System.out.println("\n任意键继续！");
                    new Scanner(System.in).nextLine();
                    break;
                }
                //查看所有房间信息
                case 4:{
                    Room[][] rooms= hotel.getRooms();
                    System.out.println("=============================***********==============================");
                    System.out.println("                             所有房间信息                              ");
                    System.out.println("-----------------------------***********------------------------------");
                    for (int i = 1; i < rooms.length; i++) {
                        for (int j = 1; j < rooms[i].length; j++) {
                            System.out.printf("房间号：%3d          房间类型：%5s          房间状态：%5s %n",
                                    rooms[i][j].getNumber(),
                                    rooms[i][j].getType(),
                                    rooms[i][j].getConditionStr());
                        }
                    }
                    System.out.println("======================================================================");
                    System.out.println("\n任意键继续！");
                    new Scanner(System.in).nextLine();
                    break;
                }
                case 5:{
                    System.out.print("请输入您要查询的房间号：");
                    int num = new Scanner(System.in).nextInt();
                    if(!hotel.isExistRoom(num)){
                        System.out.println("无该房间！");
                        break;
                    }
                    Room room = hotel.getRoomObj(num);
                    System.out.println("=============================*******==============================");
                    System.out.println("                             房间信息                              ");
                    System.out.println("-----------------------------*******------------------------------\n");
                    System.out.printf("房间号：%3d          房间类型：%5s          房间状态：%5s %n",
                            room.getNumber(),
                            room.getType(),
                            room.getConditionStr());

                    System.out.println("\n=================================================================");
                    System.out.println("\n任意键继续！");
                    new Scanner(System.in).nextLine();
                    break;
                }
                case 0:{
                    isline = false;
                    break;
                }
                default:
                    System.out.print("输入错误！请重新选择：");
                    break;
            }
        }
    }


    /**
     * 下面是Rooms的所有相关操作
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * @param storey 酒店层数（最多九层）
     * @param room 没层房间数（最多99间）
     * 作用：初始化酒店的所有房间，每双层的双号为双人间。
     *//*
    static boolean initRooms(Room rooms[][], int storey, int room){
        int num;
        String type;
        for (int i = 1; i <= storey; i++) {
            for (int j = 1; j <= room; j++) {
                num = i*100 + j;
                type = "单人间";
                if(i%2 == 0){
                    if(j%2 == 0){
                        type = "双人间";
                    }
                }
                rooms[i][j] = new Room(num,type,0);
            }
        }
        return true;
    }*/

    /**
     * 转换状态信息   将数字的状态信息转化成字符串
     * @param condition 数字状态信息
     * @return 字符串状态信息
     */
    /*static String checkCondition(int condition){
        String SCondition;
        if(condition == 0){
            SCondition = "闲置";
        } else if(condition == 1){
            SCondition = "被预约";
        }else{
            SCondition = "占用";
        }
        return SCondition;
    }*/

    /**
     * 按照房间号查找对应下标
     * @param rooms 所有房间数组
     * @param RoomNumber 要找的房间号
     * @return 返回下标数组（第一位为行，第二位为列）
     *//*
    static int[] sreachRoom(Room rooms[][],int RoomNumber){
        int[] arr = {0,0};
        for (int i = 1; i < rooms.length; i++) {
            for (int j = 1; j < rooms[i].length; j++) {
                if(RoomNumber == rooms[i][j].getNumber()){
                    arr[0] = i;arr[1] = j;
                }
            }
        }
        return arr;
    }*/
    /**
     *+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    //确认密码，输入两次一致则通过
    static String vaildatePassword(){
        Scanner s = new Scanner(System.in);
        String password;
        while(true) {
            System.out.print("请输入密码：");
            password = new Scanner(System.in).nextLine();
            System.out.print("请再次输入密码：");
            if (password.equals(new Scanner(System.in).nextLine()))
                break;
            else
                System.out.println("两次密码不一致！");
        }
        return password;
    }
}