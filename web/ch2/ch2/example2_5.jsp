<%@ page contentType="text/html;charset=GB2312" %>
<HTML><body bgcolor=cyan>
  <%  //Math.random()�ǣ�0,1��֮��������
      int number = 7+(int)(Math.random()*13);
      if(number<=13) {
  %>   <div style="text-align: center;"> <h2> ��ʾСѧ��ͼƬ</h2> <!-- ����������� -->
       <image src ='image/xiao.jpg' width=180 height=178>Сѧ��</image>
  <%  }
      else {
  %>   <div style="text-align: center;"> <h2> ��ʾ��ѧ��ͼƬ</h2>
       <image src ='image/zhong.jpg' width=180 height=178>��ѧ��</image>
  <%  }
  %> 
</body></HTML>
