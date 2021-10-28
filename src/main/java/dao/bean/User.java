package dao.bean;
public class User {  //模型层里的实体类
		private String username;  //类属性
		private String password;
		private String realname;
		private String mobile;
		private int age;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRealname() {
			return realname;
		}
		public void setRealname(String realname) {
			this.realname = realname;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
		//本项目里，下面的无参构造方法可去，因为未使用JavaBean
		public User() { 
			// 空实现也是实现
		}

		//本项目里，下面的方法是可去的。
		@Override
		public String toString() {
			return "User [username=" + username + ", password=" + password + ", realname=" + realname + ", mobile="
					+ mobile + ", age=" + age + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword()
					+ ", getRealname()=" + getRealname() + ", getMobile()=" + getMobile() + ", getAge()=" + getAge()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
					+ "]";
		}
		
		
}
