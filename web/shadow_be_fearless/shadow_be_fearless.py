import base64
import datetime

import time
from flask import Flask, render_template, request,session,redirect,url_for,jsonify
from DBConnection import Db

app = Flask(__name__)
app.secret_key="hai"
static_path="D:\\Riss\\Project_2021\\shadow_be_fearless\\"
@app.route('/adm_admin')
def admin_home():
    return render_template("admin/Admin_home.html")


#=======================================index pages loading ===========================
@app.route('/')
def logintemp():
    return render_template("log.html")

@app.route('/adminindex')
def adminindex():
    return render_template("admin/admindex.html")


@app.route('/subindex')
def subindex():
    return render_template("subadmin/subindex.html")


#=======================================login method ===========================

@app.route('/ff')
def login():
    return render_template("login.html")


@app.route("/login_post",methods=['post'])
def login_post():
    db=Db()
    username=request.form['username']
    password=request.form['password']
    qry="SELECT * FROM login WHERE username='"+username+"' AND password='"+password+"'"
    res=db.selectOne(qry)
    if res is not None:
        mid = res["login_id"]
        if res['type']=="admin":
            print("hhh")
            return render_template("shadow1.html")
        elif res["type"]=="subadmin":
            session['lid']=mid
            return render_template("shadow2.html")
        elif res["type"]=="police":
            session['lid']=mid
            return render_template("Police/polindex.html")
        else:
            return "<script>alert('No user found');window.location='/'</script>"
    else:
        return "<script>alert('Invalid username and password');window.location='/'</script>"


#=======================================notification operations ===========================

@app.route("/adm_add_notif")
def adm_add_notif():
    return render_template("admin/Add_Notification.html")


@app.route("/adm_add_notif_post",methods=['post'])
def  adm_add_notif_post():
    db=Db()
    message=request.form['textarea']
    qry="INSERT INTO `notification`(`Message`,`Date`)VALUES('"+message+"',CURDATE())"
    res=db.insert(qry)
    return adm_add_notif()


@app.route("/adm_edit_notif/<lid>")
def adm_edit_notif(lid):
    ss=Db()
    qry=" SELECT * FROM notification WHERE Notification_id='"+lid+"'"
    res=ss.selectOne(qry)
    session['eid'] = lid
    return render_template("admin/Edit_Notification.html",data=res)
@app.route("/adm_edit_notif_post",methods=['post'])
def adm_edit_notif_post():
   db = Db()
   message=request.form['textarea']
   qry="UPDATE `notification` SET `Message`='"+message+"' WHERE `Notification_id`='"+str(session['eid'])+"' "
   db.update(qry)
   return "<script>alert('Notification updated successfully');window.location='/adm_view_notif'</script>"



#=======================================police information adding ===========================

@app.route("/adm_add_police")
def adm_add_police():
    return render_template("admin/Add_police.html")
@app.route("/adm_add_police_post",methods=['post'])
def adm_add_police_post():
    db = Db()
    name=request.form['textfield']
    gender=request.form['gen']
    dob=request.form['textfield2']
    station_name=request.form['textfield5']
    city=request.form['textfield6']
    district=request.form['select2']
    state=request.form['select']
    pin=request.form['textfield7']
    phone_no=request.form['textfield3']
    email_id=request.form['textfield4']

    lat = request.form['lt']
    lon = request.form['lg']
    pl = request.form['pl']

    image=request.files['pic']
    image.save(static_path+"//static//police_images//"+image.filename)
    path="/static/police_images/"+image.filename
    qry1="INSERT INTO `login`(`username`,`password`,`type`)VALUES('"+email_id+"','"+phone_no+"','police')"
    res1=db.insert(qry1)
    qry="INSERT INTO police(NAME,Gender,DOB,Station_Name,City,District,State,Pin,Phone_Number,Email_id,Image,Login_id,lati,longi,place) VALUES('"+name+"','"+gender+"','"+dob+"','"+station_name+"','"+ city+"','"+district+"','"+state+"','"+pin+"','"+phone_no+"','"+ email_id+"','"+path+"','"+str(res1)+"','"+lat+"','"+lon+"','"+pl+"')"
    res=db.insert(qry)
    return adm_add_police()

#=======================================vehicle info adding ===========================

@app.route("/adm_add_vehicle")
def adm_add_vehicle():
     return render_template("admin/Add_VEHICLE.html")
@app.route("/adm_add_vechile_post",methods=['post'])
def adm_add_vehicle_post():
    db = Db()
    reg_no=request.form['textfield']
    chassis_no=request.form['textfield5']
    type_of_vehicle=request.form['select2']
    qry="INSERT INTO vehicle(Register_Number,Chasis_Number,Type_of_Vehicle) VALUES('"+reg_no+"','"+chassis_no+"','"+type_of_vehicle+"')"
    res=db.insert(qry)
    return adm_add_vehicle()

@app.route("/adm_edit_vehicle/<vid>")
def adm_edit_vehicle(vid):
    ss = Db()
    qry = " SELECT * FROM vehicle WHERE Vehicle_id='" + vid + "'"
    res = ss.selectOne(qry)
    session['eid'] = vid
    return render_template("admin/Edit_VEHICLE.html",data=res)
@app.route("/adm_edit_vehicle_post",methods=['post'])
def adm_edit_vehicle_post():
    db = Db()
    reg_no=request.form['textfield']
    chassis_no=request.form['textfield5']
    type_of_vehicle=request.form['select2']
    qry = "UPDATE `vehicle` SET Register_Number='"+reg_no+"',Chasis_Number='"+chassis_no+"',Type_of_Vehicle='"+type_of_vehicle+"' WHERE Vehicle_id='"+ str(session['eid'])+"' "
    db.update(qry)
    return adm_view_vehicle()


# /========================== site information added ========================================

@app.route("/adm_add_site_info")
def adm_add_site_info():
    return render_template("admin/site_info_managment.html")
@app.route("/adm_add_site_info_post",methods=['post'])
def adm_add_site_info_post():
    db = Db()
    message=request.form['textarea']
    qry="INSERT INTO site_info(Message) VALUES('"+message+"')"
    res=db.insert(qry)
    return adm_add_site_info()


# /========================== subadmin added ========================================

@app.route("/adm_add_subadmin")
def adm_add_subadmin():
    return render_template("admin/add_subadmin.html")
@app.route("/adm_add_subadmin_post",methods=['post'])
def adm_add_subadmin_post():
    db = Db()
    name = request.form['textfield']
    gender = request.form['gen']
    dob = request.form['textfield2']
    print(dob)
    city = request.form['textfield6']
    district = request.form['select2']
    state = request.form['select']
    pin = request.form['textfield7']
    phone_no = request.form['textfield3']
    email_id = request.form['textfield4']
    image = request.files['pic']
    image.save(static_path+"//static//subadmin_images//" + image.filename)
    path = "/static/subadmin_images/" + image.filename
    qry1= "INSERT INTO `login`(`username`,`password`,`type`)VALUES('"+email_id+"','"+phone_no+"','subadmin')"
    res1=db.insert(qry1)
    qry ="INSERT INTO subadmin(NAME,Gender,DOB,City,District,State,Pin,Phone_Number,Email_id,Image,Login_id) VALUES('"+name+"','" + gender + "','" + dob + "','" + city + "','" + district + "','" + state + "','" + pin + "','" + phone_no + "','" + email_id + "','"+path+"','"+str(res1)+"')"
    res = db.insert(qry)
    return adm_add_subadmin()


# /========================== allocations of vehicles and police  ========================================

@app.route("/adm_add_allocation")
def adm_add_allocation():
    db=Db()
    qry="SELECT * FROM police"
    qry1="SELECT * FROM vehicle"
    res=db.select(qry)
    res1=db.select(qry1)
    return render_template("admin/vehicle_allocation.html",data=res,data1=res1)



@app.route("/adm_add_allocation_post",methods=['post'])
def adm_add_allocation_post():
    db = Db()
    vehicle=request.form['select']
    police=request.form['select2']
    db=Db()
    qry="INSERT INTO vehicle_allocation(Vehicle_id,Police_id,Allocated_Date) VALUES('"+vehicle+"','"+police+"',curdate())"
    db.insert(qry)
    return adm_add_allocation()




@app.route("/adm_view_allocation")
def adm_view_allocation():
     c=Db()
     qry="SELECT vehicle_allocation.Allocate_id,police.Name,police.Image,vehicle.Register_Number FROM vehicle_allocation, police, vehicle WHERE vehicle_allocation.Police_id=police.Login_id AND vehicle_allocation.Vehicle_id=vehicle.Vehicle_id"
     res=c.select(qry)
     return render_template("admin/view vehicle allocation.html",data=res)

@app.route("/adm_delete_allocation/<aid>")
def adm_delete_allocation(aid):
    c=Db()
    qry="DELETE FROM vehicle_allocation WHERE Allocate_id='"+aid+"'"
    c.delete(qry)
    return adm_view_allocation()


# /========================== blocked users info ========================================


@app.route("/adm_view_blocked_user")
def adm_view_blocked_user():
    c=Db()
    qry="SELECT USER.*,login.* FROM USER,login WHERE user.Login_id=login.Login_id AND login.type='user'"
    res=c.select(qry)
    return render_template("admin/View_Blocked user.html",data=res)

@app.route("/adm_view_blocked_user_serr",methods=['post'])
def adm_view_blocked_user_serr():
    c=Db()
    name=request.form['textfield']
    qry="SELECT USER.*,login.* FROM USER,login WHERE user.Login_id=login.Login_id AND login.type='block' AND user.Name LIKE '%"+name+"%'"
    print(qry)
    res=c.select(qry)
    return render_template("admin/View_Blocked user.html", data=res)


@app.route("/adm_view_blocked_unblock/<kk>")
def adm_view_blocked_unblock_post(kk):
    c=Db()
    qry="UPDATE login SET TYPE='block' WHERE login_id='"+kk+"'"
    res=c.update(qry)
    return  redirect(url_for('adm_view_blocked_user'))


@app.route("/adm_view_blocked_user_unblock",methods=['post'])
def adm_view_blocked_user_unblock():
    c=Db()
    name=request.form['textfield']
    qry="SELECT USER.*,login.* FROM USER,login WHERE user.Login_id=login.Login_id AND login.type='block' AND user.Name LIKE '%"+name+"%'"
    print(qry)
    res=c.select(qry)
    return render_template("admin/View_Register_user and Block user.html", data=res)



# /========================== feedback =======================================


@app.route("/adm_view_feedback")
def adm_view_feedback():
    c=Db()
    qry="SELECT user.*,feedback.* FROM feedback,USER WHERE feedback.User_id=user.Login_id;"
    res=c.select(qry)
    return render_template("admin/view_Feedback.html",data=res)



@app.route("/sub_view_feedback")
def sub_view_feedback():
    c=Db()
    qry="SELECT user.*,feedback.* FROM feedback,USER WHERE feedback.User_id=user.Login_id;"
    res=c.select(qry)
    return render_template("subadmin/view_Feedback.html",data=res)

@app.route("/adm_view_notif")
def adm_view_notif():
    c=Db()
    qry="SELECT * FROM notification"
    res=c.select(qry)
    return render_template("admin/vIew_notification.html",data=res)
@app.route("/adm_delete_notif/<nid>")
def adm_delete_notif(nid):
    c=Db()
    qry="DELETE FROM notification WHERE Notification_id='"+nid+"'"
    c.delete(qry)
    return adm_view_notif()




@app.route("/adm_view_register")
def adm_view_register_post():
    c=Db()
    qry="SELECT USER.*,login.* FROM USER,login WHERE user.Login_id=login.Login_id AND login.type='block'"
    res=c.select(qry)
    return render_template("admin/View_Register_user and Block user.html",data=res)



@app.route("/adm_view_register_postssss",methods=['post'])
def adm_view_register_postssss():
    c=Db()
    name=request.form['textfield']
    qry="SELECT USER.*,login.* FROM USER,login WHERE user.Login_id=login.Login_id AND login.type='user' AND user.Name LIKE '%"+name+"%'"
    res=c.select(qry)
    return render_template("admin/View_Register_user and Block user.html",data=res)




@app.route("/adm_view_register_block/<kk>")
def adm_view_register_block_post(kk):
    c=Db()
    qry="UPDATE login SET TYPE='user' WHERE login_id='"+kk+"'"
    res=c.update(qry)
    return adm_view_register_post()




@app.route("/adm_edit_site/<iid>")
def adm_edit_site(iid):
    ss = Db()
    qry = " SELECT * FROM site_info WHERE Info_id='" + iid + "'"
    res = ss.selectOne(qry)
    session['eid'] = iid
    return render_template("admin/site_info_managment.html",data=res)
@app.route("/adm_edit_siteee_post",methods=['post'])
def adm_edit_siteee_post():
    db = Db()
    message=request.form['textarea']
    qry = "UPDATE site_info SET Message='"+message+"' WHERE Info_id='"+ str(session['eid'])+"'"
    db.update(qry)
    return adm_view_site()


@app.route("/adm_view_site")
def adm_view_site():
    c=Db()
    qry="SELECT * FROM site_info"
    res=c.select(qry)
    return render_template("admin/view_siteinfo_managment.html",data=res)
@app.route("/adm_delete_site/<iid>")
def adm_delete_site(iid):
    c=Db()
    qry="DELETE FROM site_info WHERE Info_id='"+iid+"'"
    c.delete(qry)
    return adm_view_site()



@app.route("/adm_edit_subadmin/<did>")
def adm_edit_subadmin(did):
    c=Db()
    qry1 = "SELECT * FROM subadmin WHERE Subadmin_id='"+did+"'"
    res1 = c.selectOne(qry1)
    print(res1)
    session['eid'] = did
    return render_template("admin/Subadmin_management.html",data=res1)

@app.route("/adm_edit_subadm_post",methods=['post'])
def  adm_edit_subadm_post():
    db = Db()
    name=request.form['textfield']
    print(name)
    gender=request.form['gen']
    print(gender)
    dob=request.form['textfield2']
    print(dob)
    city=request.form['textfield6']
    print(city)
    district=request.form['select2']
    print(district)
    state=request.form['select']
    print(state)
    pin=request.form['textfield7']
    print(pin)
    phone_no=request.form['textfield3']
    print(phone_no)
    email_id=request.form['textfield4']
    print(email_id)
    if 'fileField' not in request.files:
        db=Db()
        qry = "UPDATE subadmin SET NAME='"+name+"',Gender='"+gender+"',DOB='"+dob+"',City='"+city+"',District='"+district+"',State='"+state +"',Pin='" +pin+"',Phone_Number='"+phone_no+"',Email_id='"+email_id+"' WHERE Subadmin_id='"+str(session['eid'])+"'"
        res=db.update(qry)
        return adm_view_subadmin()
    else:
        hi = request.files['fileField']
        if hi.filename=='':
            qry = "UPDATE subadmin SET NAME='"+name+"',Gender='"+gender+"',DOB='"+dob+"',City='"+city+"',District='"+district+"',State='" +state+"',Pin='"+pin+"',Phone_Number='"+phone_no+"',Email_id='"+email_id+"' WHERE Subadmin_id='"+str(session['eid']) + "'"
            db = Db()
            res=db.update(qry)
            return adm_view_subadmin()
        else:
            image = request.files['fileField']
            image.save(static_path+"//static//subadmin_images//" + image.filename)
            path = "/static/subadmin_images/" + image.filename
            qry="UPDATE subadmin SET NAME='"+name+"',Gender='"+gender+"',DOB='"+dob+"',City='"+city+"',District='"+district+"',State='"+state+"',Pin='"+pin+"',Phone_Number='"+phone_no+"',Email_id='"+email_id+"',Image='"+path+"' WHERE Subadmin_id='"+str(session['eid'])+"' "
            print(qry)
            db = Db()
            res =db.update(qry)
            return adm_view_subadmin()


@app.route("/adm_search_subadmin_post",methods=['post'])
def adm_search_subadmin_post():
    c=Db()
    n=request.form["textfield"]
    qry="SELECT * FROM `subadmin` WHERE `Name` LIKE '%"+n+"%'"
    res=c.select(qry)
    return render_template("admin/view_subadmin.html",data=res)








@app.route("/adm_view_subadmin")
def adm_view_subadmin():
    c=Db()
    qry="SELECT * FROM subadmin"
    res=c.select(qry)
    return render_template("admin/view_subadmin.html",data=res)
@app.route("/adm_delete_subadmin/<did>")
def add_delete_subadmin(did):
    c=Db()
    qry="DELETE FROM subadmin WHERE subadmin_id='"+did+"'"
    c.delete(qry)
    return adm_view_subadmin()






@app.route("/adm_view_vehicle")
def adm_view_vehicle():
    c=Db()
    qry="SELECT * FROM vehicle"
    res=c.select(qry)
    return render_template("admin/vIew_vehicle_Management.html",data=res)
@app.route("/adm_delete_vehicle/<vid>")
def adm_delete_vehicle(vid):
    c=Db()
    qry="DELETE FROM vehicle WHERE vehicle_id='"+vid+"'"
    c.delete(qry)
    return adm_view_vehicle()



@app.route("/adm_view_police")
def adm_view_police():
    c=Db()
    qry="SELECT * FROM police"
    res=c.select(qry)
    return render_template("admin/view_police_managment.html",data=res)
@app.route("/adm_delete_police/<pid>")
def adm_delete_police(pid):
    c=Db()
    qry="DELETE FROM police WHERE Police_id='"+pid+"'"
    c.delete(qry)
    return adm_view_police()



@app.route("/adm_edit_police/<pid>")
def adm_edit_police(pid):
    c=Db()
    qry1 = "SELECT * FROM police WHERE Police_id='"+pid+"'"
    res1 = c.selectOne(qry1)
    session['eid'] = pid
    return render_template("admin/EDIT_police.html",data=res1)

@app.route("/adm_edit_police_post",methods=['post'])
def  adm_edit_police_post():
    db = Db()
    name=request.form['textfield']
    print(name)
    gender=request.form['gen']
    print(gender)
    dob=request.form['textfield2']
    print(dob)
    station_name=request.form['textfield5']
    print(station_name)
    city=request.form['textfield6']
    print(city)
    district=request.form['select2']
    print(district)
    state=request.form['select']
    print(state)
    pin=request.form['textfield7']
    print(pin)
    phone_no=request.form['textfield3']
    print(phone_no)
    email_id=request.form['textfield4']
    print(email_id)
    if 'fileField' not in request.files:
        db=Db()
        qry = "UPDATE `police` SET NAME='"+name+"',Gender='"+gender+"',DOB='"+dob+"',Station_Name='"+station_name+"',City='"+city+"',District='"+district+"',State='"+state +"',Pin='" +pin+"',Phone_Number='"+phone_no+"',Email_id='"+email_id+"' WHERE Police_id='"+str(session['eid'])+"'"
        res=db.update(qry)
        return adm_view_police()
    else:
        hi = request.files['fileField']
        if hi.filename=='':
            qry = "UPDATE `police` SET NAME='"+name+"',Gender='"+gender+"',DOB='"+dob+"',Station_Name='"+station_name+"',City='"+city+"',District='"+district+"',State='" +state+"',Pin='"+pin+"',Phone_Number='"+phone_no+"',Email_id='"+email_id+"' WHERE Police_id='"+str(session['eid']) + "'"
            db = Db()
            res=db.update(qry)
            return adm_view_police()
        else:
            image = request.files['fileField']
            image.save(static_path+"//static//subadmin_images//" + image.filename)
            path = "/static/subadmin_images/" + image.filename
            qry="UPDATE `police` SET NAME='"+name+"',Gender='"+gender+"',DOB='"+dob+"',Station_Name='"+station_name+"',City='"+city+"',District='"+district+"',State='"+state+"',Pin='"+pin+"',Phone_Number='"+phone_no+"',Email_id='"+email_id+"',Image='"+path+"' WHERE Police_id='"+str(session['eid'])+"' "
            print(qry)
            db = Db()
            res =db.update(qry)
            return adm_view_police()


#subadmin start

@app.route('/sa_home')
def sa_home():
    return render_template("subadmin/sub.index.html")

@app.route('/sa_view_profile')
def sa_view_profile():
    print(session['lid'])
    c=Db()
    qry="select * from subadmin where Login_id='"+str(session['lid'])+"'"
    res=c.selectOne(qry)
    print(res)
    return render_template("subadmin/view_profile.html",data=res)

@app.route('/sa_changepassword')
def sa_changepassword():
    return render_template("subadmin/change_password.html")

@app.route('/sa_changepassword_post',methods=['post'])
def sa_changepassword_post():
    db=Db()
    currentpassword=request.form['textfield']
    new=request.form['textfield2']

    conpassword=request.form['textfield3']
    if new==conpassword:
        qry="SELECT password FROM login WHERE `login_id`='"+str(session['lid'])+"'"
        res=db.selectOne(qry)

        if res is not None:
            if res['password']==currentpassword:
                qry="UPDATE `login` SET password='"+conpassword+"'WHERE `login_id`='"+str(session['lid'])+"'"
                res=db.update(qry)
                return "<script>alert('successfully changed password');window.location='/'</script>"
            else:
                return "<script>alert('invalid account password');window.location='/sa_changepassword'</script>"
        else:
            return "<script>alert('invalid account password');window.location='/sa_changepassword'</script>"
    else:
        return "<script>alert('Password Mismatched');window.location='/sa_changepassword'</script>"


@app.route('/sa_location')
def sa_location():
    c=Db()
    qry="select * from location"
    res=c.select(qry)
    return render_template("subadmin/Location_view.html",data=res)

@app.route('/sa_add_safe')
def sa_add_safe():
    # c=Db()
    # qry="select *  from safe where Subadmin_id='"+str(session['lid'])+"'"
    # res=c.select(qry)
    return render_template("subadmin/add_safe.html")

@app.route('/sa_add_safe_post',methods=['post'])
def sa_add_safe_post():
    db=Db()
    location=request.form['textfield']
    latitude=request.form['textfield2']
    longitude=request.form['textfield5']
    qry=" INSERT INTO `safe` (`Location`,`Latitude`,`Longitude`,Subadmin_id) VALUES ('"+location+"','"+latitude+"','"+longitude+"','"+str(session['lid'])+"')"
    res=db.insert(qry)
    return render_template("subadmin/add_safe.html")

@app.route('/sa_police_view')
def sa_police_view():
    c=Db()
    qry=" SELECT * from police"
    res=c.select(qry)
    return render_template("subadmin/police view.html",data=res)

@app.route('/sa_safe_point_managment')
def sa_safe_point_managment():
    c=Db()
    qry="select * from safe"
    res=c.select(qry)
    return render_template("subadmin/view_safe_point.html",data=res)
@app.route('/sa_delete_safe_point/<nid>')
def sa_delete_safe_point(nid):
    c=Db()
    qry="DELETE FROM safe WHERE`Safe_id`='"+nid+"' "
    c.delete(qry)
    return sa_safe_point_managment()



@app.route('/sa_dangerous_addedbyuser')
def sa_dangerous_addedbyuser():
    c=Db()
    qry="select * from dangerous_spot where status='Approved'"
    res=c.select(qry)
    return render_template("subadmin/view_dangerous_spot.html",data=res)


@app.route('/sa_dangerous_addedbyuser_post',methods=['post'])
def sa_dangerous_addedbyuser_post():
    db=Db()
    ty=request.form["select"]
    qry = "select * from dangerous_spot where Added_Dangerous_Spot='"+ty+"'"

    res=db.select(qry)
    print(res)
    return render_template("subadmin/view_dangerous_spot.html",data=res)





@app.route('/sa_feedback')
def sa_feedback():
    c = Db()
    qry = "SELECT user.*,feedback.* FROM feedback,USER WHERE feedback.User_id=user.Login_id;"
    res = c.select(qry)
    return render_template("subadmin/view_Feedback_adminside.html",data=res)

@app.route('/sa_edit_safe_point/<tid>')
def sa_edit_safe_point(tid):
    ss=Db()
    qry="SELECT * FROM `safe` WHERE `Safe_id`='"+tid+"'"
    res=ss.selectOne(qry)
    session['eid'] = tid
    return render_template("subadmin/SAFE_point_management.html",data=res)


@app.route('/sa_edit_safe_point_post',methods=['post'])
def sa_edit_safe_point_post():
        db=Db()
        sid=request.form['sid']
        location=request.form['textfield']
        latitude=request.form['textfield2']
        longitude=request.form['textfield5']
        qry="UPDATE `safe` SET `Location`='"+location+"',Latitude='"+latitude+"',Longitude='"+longitude+"' WHERE `Safe_id`='"+sid+"'"
        db.update(qry)
        return "<script>alert('successfully updated');window.location='/sa_safe_point_managment'</script>"

@app.route('/sa_visual_send/<nn>')
def sa_visual_send(nn):
    c=Db()
    qry="SELECT `visual`.*,`police`.* FROM `visual`,`police` WHERE `visual`.`Police_id`=`police`.`Login_id` and visual.Police_id='"+nn+"'"
    res=c.select(qry)
    return render_template("subadmin/view_visual_send_by_police.html",data=res)


# police start
@app.route("/android_login_post",methods=['post'])
def android_login_post():
    print("hii")
    db=Db()
    username=request.form['username']
    password=request.form['password']
    qry="SELECT * FROM login WHERE username='"+username+"' AND password='"+password+"'"
    print(qry)
    res=db.selectOne(qry)
    if res is not None:
            return jsonify(status="ok",lid=res['login_id'],type=res['type'])
    else:
            return jsonify(status="no")












@app.route('/pc_view_profile',methods=['post'])
def pc_view_profile():
    a=request.form["lid"]
    c = Db()
    qry = "select * from police where Login_id='" + str(a) + "'"
    res = c.selectOne(qry)
    print(res)
    return jsonify(status="ok",Name=res['Name'],Gender=res['Gender'],Dob=res['DOB'],Stationname=res['Station_Name'],City=res['City'],District=res['District'],State=res['State'],Pin=res['Pin'],Phonenumber=res['Phone_Number'],Emailid=res['Email_id'],Image=res['Image'],Loginid=res['Login_id'])


@app.route('/pc_change_password_post', methods=['post'])
def pc_change_password_post():
        db = Db()
        currentpassword = request.form['textfield']
        newpassword = request.form['textfield2']
        b=request.form["lid"]
        qry = "SELECT password FROM login WHERE `login_id`='" + str(b) + "'"
        res = db.selectOne(qry)
        if res is not None and res['password'] == currentpassword:
            qry = "UPDATE `login` SET password='" + newpassword + "'WHERE `login_id`='" + str(b) + "'"
            res = db.update(qry)
            return jsonify(status="ok")
        else:
            return jsonify(status="no")
        
        

@app.route('/pc_add_dangoures_spot',methods=["post"])
def pc_add_dangoures_spot():
    db=Db()
    location=request.form['textfield']
    latitude=request.form['textfield2']
    longitude=request.form['textfield3']
    c=request.form['lid']
    qry="INSERT INTO `dangerous spot`(`Location`,`Latitude`,`Longitude`,`Login_id`,Added_Dangerous_Spot,status) VALUES ('"+location+"','"+latitude+"','"+longitude+"','"+str(c)+"','police','Approved')"
    res=db.insert(qry)
    return jsonify(status="ok")
@app.route('/pc_edit_dangoures_spot_',methods=["post"])
def pc_edit_dangoures_spot_():
    db=Db()
    location=request.form['textfield']
    latitude=request.form['textfield2']
    longitude=request.form['textfield3']
    d=request.form['lid']
    qry="UPDATE `dangerous spot` SET `Location`='"+location+"',`Latitude`='"+latitude+"',Longitude='"+longitude+"' WHERE `Dangerous_id`='"+str(d)+"'"
    db.update(qry)
    return jsonify(status="ok")

@app.route('/pc_delete_dangoures_spot',methods=["post"])
def pc_delete_dangoures_spot():
    c=Db()
    f=request.form["gid"]
    qry="DELETE FROM `dangerous spot` WHERE `Dangerous_id`='"+f+"'"
    c.delete(qry)
    return jsonify(status="ok")






@app.route('/pc_view_dangerous_addedbyuser_post', methods=["post"])
def pc_view_dangerous_addedbyuser_post():
    c=Db()
    qq="SELECT * FROM `dangerous_spot` WHERE `Added_Dangerous_Spot`='user' and dangerous_spot.status='pending'"
    res=c.select(qq)
    print(res)
    return  jsonify(status='ok',data=res)




@app.route('/pc_view_complaint',methods=["post"])
def pc_view_complaint():
    db=Db()
    qq="SELECT `user`.*,`complaint`.* FROM `user`,`complaint` WHERE `user`.`User_id`=`complaint`.`Complaint_id` and Status='pending'"
    res=db.select(qq)
    return jsonify(status="ok",data=res)

@app.route('/pc_view_dangerous',methods=["post"])
def pc_view_dangerous():
    db = Db()
    qq = "SELECT * FROM `dangerous_spot` where status='Approved'"
    res = db.select(qq)
    return jsonify(status="ok",data=res)

@app.route('/pc_emergency_help',methods=["post"])
def pc_emergency_help():
    db = Db()
    qq = "SELECT `user`.*,`emergency`.* FROM `user`,`emergency` WHERE `user`.`Login_id`=`emergency`.`User_id`"
    res = db.select(qq)
    print(res)
    return jsonify(status="ok", data=res)


#user start

@app.route('/us_registration',methods=["post"])
def us_registration():
    db=Db()
    name=request.form['name']
    gender=request.form['gender']
    dob=request.form['DOB']
    housenumber=request.form['housenumber']
    city=request.form['city']
    district=request.form['district']
    state=request.form['state']
    pin=request.form['pin']
    phonenumber=request.form['phonenumber']
    emailid=request.form['emailid']
    password=request.form['password']
    image=request.form['image']
    import time
    import base64

    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(image)
    fh = open(static_path+"user\\" + timestr + ".jpg", "wb")
    path = "/static/user/" + timestr + ".jpg"
    fh.write(a)
    fh.close()
    qry1="INSERT INTO login (`username`,`password`,`type`) VALUES ('"+emailid+"','"+password+"','user')"
    h=request.form['rid']
    qry=" INSERT INTO `user`(`Name`,`Gender`,`DOB`,`House Number`,`City`,`District`,`State`,`Pin`,`Image`,`Phone_Number`,`Email_ID`,`Login_id`) VALUES ('"+name+"','"+gender+"','"+dob+"','"+housenumber+"','"+ city+"','"+district+"','"+state+"','"+pin+"','"+path+"','"+phonenumber+"','"+ emailid+"','"+str(h)+"')"
    res=db.insert(qry)
    return jsonify(status="ok")


@app.route('/us_view_profile',methods=['post'])
def us_view_profile():
    a=request.form["lid"]
    c = Db()
    qry = "SELECT * FROM user WHERE Login_id='" + str(a) + "'"
    res = c.selectOne(qry)
    print(res)
    return jsonify(status="ok",Name=res['Name'],Gender=res['Gender'],DOB=res['DOB'],housenumber=res['House_Number'],City=res['City'],District=res['District'],Pin=res['Pin'],Phonenumber=res['Phone_Number'],Emailid=res['Email_ID'],Image=res['Image'],Loginid=res['Login_id'])


@app.route('/us_change_password_post', methods=['post'])
def us_change_password_post():
    db = Db()
    currentpassword = request.form['curpass']
    print(currentpassword)
    newpassword = request.form['newpass']
    print(newpassword)

    conpass=request.form['conpass']
    print(conpass)

    b = request.form["lid"]
    qry = "SELECT password FROM login WHERE `login_id`='" + str(b) + "'"
    res = db.selectOne(qry)
    if newpassword==conpass:
        if res is not None:
            if res['password'] == currentpassword:
                qry = "UPDATE `login` SET password='" + newpassword + "'WHERE `login_id`='" + str(b) + "'"
                res = db.update(qry)
                return jsonify(status="ok")
            else:
                return jsonify(status="no")
        else:
            return jsonify(status="no")
    else:
        return jsonify(status="no")


@app.route('/us_add_emergency_number',methods=['post'])
def us_add_emergency_managment():
    db=Db()
    phonenumber=request.form['emno']
    lid=request.form['lid']
    name=request.form['name']
    qry="INSERT INTO `emergency number`(`Name`,`Phone_Number`,`User_id`) VALUES ('"+name+"','"+phonenumber+"','"+lid+"')"
    res=db.insert(qry)
    return jsonify(status="ok")




@app.route('/poladdvisuals',methods=['post'])
def poladdvisuals():
    db=Db()
    img=request.form['img']
    lid=request.form['lid']
    name=request.form['loc']
    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(img)
    dt = datetime.datetime.now()
    dd = str(dt).replace(" ", "_").replace(":", "_").replace("-", "_")
    fh = open("D:\\Riss\\Project_2021\\shadow_be_fearless\\static\\visual\\" + dd + ".jpg", "wb")
    path = "/static/visual/" + dd + ".jpg"
    fh.write(a)
    fh.close()
    qry="insert into visual(Police_id,File_Name,Location,Date)values('"+lid+"','"+path+"','"+name+"',curdate())"
    res=db.insert(qry)
    return jsonify(status="ok")


@app.route('/help_approve',methods=['post'])
def help_approve():
    db=Db()
    mm=request.form['ss']
    qry="='"+mm+"'"
    res=db.update(qry)
    return jsonify(status="ok")



@app.route('/dang_approve',methods=['post'])
def dang_approve():
    db=Db()
    dangid=request.form['dangid']
    qry="update dangerous_spot  set status='Approved' where Dangerous_id='"+dangid+"'"
    res=db.update(qry)
    return jsonify(status="ok")




@app.route('/dang_delete',methods=['post'])
def dang_delete():
    db=Db()
    db = Db()
    dangid = request.form['dangid']
    qry="update dangerous_spot  set status='Rejected' where Dangerous_id='"+dangid+"'"
    res = db.update(qry)
    return jsonify(status="ok")


@app.route('/us_add_emergency',methods=['post'])
def us_add_emergency():
    db=Db()
    lati=request.form['lati']
    longi=request.form['longi']
    loc=request.form['place']
    lid=request.form['lid']
    help="help"
    qry="insert into emergency(User_id,Date,Latitude,Longitude,Location,Help)values('"+lid+"',curdate(),'"+lati+"','"+longi+"','"+loc+"','"+help+"');"
    res=db.insert(qry)
    return jsonify(status="ok")



@app.route('/us_view_emergency_number',methods=["post"])
def us_view_emergency_number():
    db = Db()
    lid=request.form['uid']
    qq = "SELECT * FROM `emergency number` where User_id='"+lid+"'"
    res = db.select(qq)
    return jsonify(status="ok", res=res)


@app.route('/and_emergency_request',methods=["post"])
def and_emergency_request():
    db = Db()
    lid=request.form['lid']
    help="help"
    lat=request.form['lat']
    lon=request.form['lon']
    place=request.form['place']
    qq = "SELECT * FROM `emergency number` where User_id='"+lid+"'"
    res = db.select(qq)
    ss="insert into emergency(User_id,Date,Latitude,Longitude,Location,Help)values('"+lid+"',curdate(),'"+lat+"','"+lon+"','"+place+"','help')"
    bb=db.insert(ss)
    return jsonify(status="ok", data=res)


@app.route('/us_edit_emergency_number',methods=["post"])
def us_edit_emergency_number():
    db = Db()
    emerid=request.form['emerid']
    qq = "SELECT * FROM `emergency number` where Emergency_Number_id='"+emerid+"'"
    res = db.selectOne(qq)
    return jsonify(status="ok",name=res['name'],phonenumber=res['phonenumber'])

@app.route('/us_update_emergency_managment',methods=['post'])
def us_update_emergency_managment():
    db=Db()
    name=request.form['name']
    phonenumber=request.form['phonenumber']
    p=request.form['zid']
    qry="UPDATE `emergency number` SET `Name`='"+name+"',`Phone_Number`='"+phonenumber+"', WHERE `Emergency_Number_id`='"+str(p)+"'"
    db.update(qry)
    return jsonify(status="ok")
@app.route('/us_delete_emergency_managment',methods=['post'])
def us_delete_emergency_managment():
    c=Db()
    s=request.form['emerid']
    qry="DELETE FROM `emergency number` WHERE `Emergency_Number_id`='"+s+"'"
    c.delete(qry)
    return jsonify(status="ok")

@app.route('/us_view_dangerous',methods=["post"])
def us_view_dangerous():
    db = Db()
    qq = "select * from dangerous_spot where status='Approved'"
    res = db.select(qq)
    return jsonify(status="ok", data=res)


@app.route('/us_safe_point_managment',methods=["post"])
def us_safe_point_managment():
    c=Db()
    qry="select * from safe"
    res=c.select(qry)
    return jsonify(status="ok", data=res)

@app.route('/us_near_by_person',methods=['post'])
def us_near_by_person():
    latti=request.form['lati']
    longi=request.form['logi']
    c=Db()
    # qry="SELECT `user`.*,`location`.* FROM `user`,`location` WHERE `user`.`User_id`=`location`.`Location_id`"
    qry="SELECT user.*,location.*, SQRT( POW(69.1 * (Latitude - '"+latti+"'), 2) +POW(69.1 * ('"+longi+"' - Longitude) * COS(Latitude/ 57.3), 2)) AS distance FROM location,user where location.Login_id=user.Login_id ORDER BY distance"
    res=c.select(qry)
    return jsonify(status="ok", data=res)

@app.route('/android_us_add_dangoures_spot',methods=["post"])
def android_us_add_dangoures_spot():
    db=Db()
    location=request.form['Location']
    latitude=request.form['Latitude']
    longitude=request.form['Longitude']
    c=request.form['lid']
    qry="INSERT INTO `dangerous_spot`(`Location`,`Latitude`,`Longitude`,`Login_id`,Added_Dangerous_Spot,status) VALUES ('"+location+"','"+latitude+"','"+longitude+"','"+str(c)+"','user','pending')"
    res=db.insert(qry)
    return jsonify(status="ok")


@app.route('/android_ps_add_dangoures_spot',methods=["post"])
def android_ps_add_dangoures_spot():
    db=Db()
    location=request.form['Location']
    latitude=request.form['Latitude']
    longitude=request.form['Longitude']
    c=request.form['lid']
    qry="INSERT INTO `dangerous_spot`(`Location`,`Latitude`,`Longitude`,`Login_id`,Added_Dangerous_Spot,status) VALUES ('"+location+"','"+latitude+"','"+longitude+"','"+str(c)+"','police','Approved')"
    res=db.insert(qry)
    return jsonify(status="ok")


@app.route('/us_add_complaint',methods=['post'])
def us_add_complaint():
    db=Db()
    complaint=request.form['comp']
    lid=request.form['lid']
    qry="INSERT INTO `complaint`(User_id,`Complaint`,`Status`,`Reply`,`Date`)VALUES ('"+lid+"','"+complaint+"','pending','pending',CURDATE())"
    res=db.insert(qry)
    return jsonify(status="ok")



@app.route('/pol_sent_rep',methods=['post'])
def pol_sent_rep():
    db=Db()
    complaint=request.form['Location']
    lid=request.form['compid']
    qry="update complaint set Reply='"+complaint+"',Status='ok' where Complaint_id='"+lid+"'"
    res=db.update(qry)
    return jsonify(status="ok")


@app.route('/us_view_complaint',methods=["post"])
def us_view_complaint():
    db=Db()
    uid=request.form['uid']
    qq="SELECT * FROM `complaint` WHERE `User_id`='"+uid+"'"
    res=db.select(qq)

    return jsonify(status="ok",data=res)

@app.route('/us_emergency_help',methods=["post"])
def us_emergency_help():
    db = Db()
    lid=request.form['lid']
    qq = "SELECT `emergency`.* FROM `user`,`emergency` WHERE `user`.Login_id=`emergency`.User_id and `emergency`.User_id='"+lid+"'"
    res = db.select(qq)
    return jsonify(status="ok", data=res)




@app.route("/us_view_notif",methods=['post'])
def us_view_notif():
    c=Db()
    qry="SELECT * FROM notification"
    res=c.select(qry)
    return jsonify(status="ok", data=res)

@app.route('/us_add_idea',methods=['post'])
def us_add_idea():
    db=Db()
    ideatittle=request.form['ideatittle']
    ideadetails=request.form['ideadetails']
    did=request.form["lid"]
    qry="INSERT INTO `idea`(`Idea_Tittle`,`Idea_Details`,`Date`,`User_id`) VALUES ('"+ideatittle+"','"+ideadetails+"',CURDATE(),'"+str(did)+"')"
    res=db.insert(qry)
    return jsonify(status="ok")



@app.route("/us_view_idea",methods=['post'])
def us_view_idea():
    c=Db()
    qry="SELECT * FROM `idea`"
    res=c.select(qry)
    return jsonify(status="ok", data=res)

@app.route('/us_chat_users',methods=['post'])
def us_chat_users():
    c=Db()
    lid = request.form['uid']
    qry="select * from user where Login_id!='"+lid+"'"
    res=c.select(qry)
    return jsonify(status="ok", data=res)

@app.route('/us_chat_pol',methods=['post'])
def us_chat_pol():
    c=Db()
    qry="select * from police"
    res=c.select(qry)
    return jsonify(status="ok", data=res)

#public start



@app.route("/pb_view_spot",methods=['post'])
def pb_view_spot():
    c=Db()
    qry="SELECT `safe`.*,`dangerous spot`.* FROM `safe`,`dangerous spot` WHERE `safe`.`Safe_id`=`dangerous spot`.`Dangerous_id`"
    res=c.select(qry)
    return jsonify(status="ok", res=res)


@app.route("/pb_view_police")
def pb_view_police():
    c=Db()
    qry="SELECT * FROM police"
    res=c.select(qry)
    return jsonify(status="ok", res=res)



@app.route('/in_message2', methods=['POST'])
def message():
    db=Db()
    fr_id = request.form["fid"]
    to_id = request.form["toid"]
    message = request.form["msg"]
    query7 = "insert into chat(From_id,To_id,Message,date) values ('" + fr_id + "' ,'" + to_id + "','" + message + "',curdate())"
    print(query7)
    res=db.insert(query7)
    return jsonify(status='send')


@app.route('/view_message2', methods=['POST'])
def msg():
    db=Db()
    fid = request.form["fid"]
    toid = request.form["toid"]
    lmid = request.form['lastmsgid']
    query="select * from chat where Chat_id>'"+lmid+"' AND ((To_id='"+toid+"' and  From_id='"+fid+"') or (To_id='"+fid+"' and From_id='"+toid+"')  )  order by Chat_id asc"
    res=db.select(query)
    print(res)
    return jsonify(status='ok', res1=res)



@app.route('/and_user_registration',methods=['POST'])
def and_user_registration():
    c=Db()
    name=request.form['name']
    dob=request.form['dob']
    place=request.form['place']
    post=request.form['post']
    gen = request.form['gen']
    pin=request.form['pin']
    ph=request.form['phone']
    im = request.form["img"]
    city = request.form["city"]
    mail = request.form["email"]
    hn=request.form['hn']
    import  base64
    import  datetime
    a = base64.b64decode(im)
    dt = datetime.datetime.now()
    dd = str(dt).replace(" ", "_").replace(":", "_").replace("-", "_")
    fh = open("D:\\Riss\\Project_2021\\shadow_be_fearless\\static\\user\\" + dd + ".jpg", "wb")
    path = "/static/user/" + dd + ".jpg"
    fh.write(a)
    fh.close()
    qry1 = "INSERT INTO login (`username`,`password`,`type`) VALUES ('" + mail + "','" + ph + "','user')"
    h = c.insert(qry1)
    qry = " INSERT INTO `user`(`Name`,`Gender`,`DOB`,`House_Number`,`City`,`District`,`Pin`,`Image`,`Phone_Number`,`Email_ID`,`Login_id`) VALUES ('" + name + "','" + gen + "','" + dob + "','" + hn + "','" + city + "','" + place + "','" + pin + "','" + path + "','" + ph + "','" + mail + "','" + str(h) + "')"
    res = c.insert(qry)
    return jsonify(status="ok")





#==============================================================================#
@app.route("/poladddang")
def poladddang():
    return render_template("Police/add_dangoures_spot.html")


@app.route("/poladddang_post",methods=['post'])
def  poladddang_post():
    db=Db()
    loc=request.form['a']
    lat = request.form['b']
    long = request.form['c']
    qry="INSERT INTO `dangerous_spot`(`Location`,`Latitude`,`Longitude`,`Login_id`,Added_Dangerous_Spot,status) VALUES ('"+loc+"','"+lat+"','"+long+"','"+str(session['lid'])+"','police','Approved')"
    res=db.insert(qry)
    return poladddang()


@app.route("/polviewuseradddang")
def polviewuseradddang():
    db = Db()
    qq="SELECT * FROM `dangerous_spot` WHERE `Added_Dangerous_Spot`='user' and dangerous_spot.status='pending'"
    res = db.select(qq)
    return render_template("Police/dangerous_spot_addedby_user.html",data=res)


@app.route("/polviewuseradddang1")
def polviewuseradddang1():
    db = Db()
    qq="SELECT * FROM `dangerous_spot` WHERE  dangerous_spot.status='Approved'"
    res = db.select(qq)
    return render_template("Police/dangapprovedspot.html",data=res)


@app.route('/pol_view_dangerous_approve/<nn>')
def pol_view_dangerous_approve(nn):
    db = Db()
    qry="update dangerous_spot  set status='Approved' where Dangerous_id='"+nn+"'"
    res = db.update(qry)
    return polviewuseradddang()


@app.route('/pol_view_dangerous_reject/<nn>')
def pol_view_dangerous_reject(nn):
    db = Db()
    qry="update dangerous_spot  set status='Rejected' where Dangerous_id='"+nn+"'"
    res = db.update(qry)
    return polviewuseradddang()



@app.route('/pol_view_complaint')
def pol_view_complaint():
    db=Db()
    qq="SELECT `user`.*,`complaint`.* FROM `user`,`complaint` WHERE `user`.`User_id`=`complaint`.`Complaint_id` and Status='pending'"
    res=db.select(qq)
    return render_template("Police/view_complaints_and_action.html",data=res)


@app.route('/pol_view_emerge')
def pol_view_emerge():
    db=Db()
    qq="select emergency.*,user.* from emergency,user where user.Login_id=emergency.User_id "
    res=db.select(qq)
    return render_template("Police/view_emergency_help.html",data=res)


@app.route('/pol_pro')
def pol_pro():
    db=Db()
    qq="select * from police where Login_id='"+str(session['lid'])+"'"
    res=db.selectOne(qq)
    return render_template("Police/prof.html",data=res)


@app.route('/senting_replys/<nn>')
def senting_replys(nn):
    session['nn']=nn
    return render_template("Police/sent_reply.html")



@app.route('/sents',methods=['post'])
def sents():
    db=Db()
    x=session['nn']
    comp=request.form['c']
    qq="update complaint set Reply='"+comp+"', Status='ok' where Complaint_id='"+comp+"'"
    res=db.update(qq)
    return pol_view_complaint()

@app.route('/pol_changepassword')
def pol_changepassword():
    return render_template("Police/change_password.html")

@app.route('/pol_changepassword_post',methods=['post'])
def pol_changepassword_post():
    db=Db()
    currentpassword=request.form['textfield']
    new=request.form['textfield2']

    conpassword=request.form['textfield3']
    if new==conpassword:
        qry="SELECT password FROM login WHERE `login_id`='"+str(session['lid'])+"'"
        res=db.selectOne(qry)

        if res is not None:
            if res['password']==currentpassword:
                qry="UPDATE `login` SET password='"+conpassword+"'WHERE `login_id`='"+str(session['lid'])+"'"
                res=db.update(qry)
                return "<script>alert('successfully changed password');window.location='/'</script>"
            else:
                return "<script>alert('invalid account password');window.location='/pol_changepassword'</script>"
        else:
            return "<script>alert('invalid account password');window.location='/pol_changepassword'</script>"
    else:
        return "<script>alert('Password Mismatched');window.location='/pol_changepassword'</script>"

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0')


