package com.zjut.oa.tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.mvc.domain.Academy;
import com.zjut.oa.mvc.domain.Department;
import com.zjut.oa.mvc.domain.Job;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.mvc.domain.strengthen.UserTogether;

public class JExcelTool {

	private static final Log log = LogFactory.getLog(JExcelTool.class);

	@SuppressWarnings("deprecation")
	public static void exportUserToOutputStream(String savefilename,
			List<UserTogether> utList, HttpServletResponse resp) {
		WritableWorkbook wwb = null;
		try {
			resp.setHeader("Content-disposition", "attachment; filename="
					+ URLEncoder.encode(savefilename));
			ServletOutputStream sos = resp.getOutputStream();
			wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);
			// 格式
			// WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
			// WritableFont.BOLD, true);
			// WritableCellFormat wcfF = new WritableCellFormat(wf);

			// 表头
			Label label_00 = new Label(0, 0, "学号");
			Label label_10 = new Label(1, 0, "姓名");
			Label label_20 = new Label(2, 0, "所在部门");
			Label label_30 = new Label(3, 0, "职务");
			Label label_40 = new Label(4, 0, "邮箱");
			Label label_50 = new Label(5, 0, "短号");
			Label label_60 = new Label(6, 0, "手机");
			Label label_70 = new Label(7, 0, "所在校区");
			Label label_80 = new Label(8, 0, "所在学院");
			Label label_90 = new Label(9, 0, "专业班级");
			Label label_100 = new Label(10, 0, "宿舍");
			Label label_110 = new Label(11, 0, "状态");
			Label label_120 = new Label(12, 0, "添加时间");
			Label label_130 = new Label(13, 0, "编辑时间");

			ws.addCell(label_00);
			ws.addCell(label_10);
			ws.addCell(label_20);
			ws.addCell(label_30);
			ws.addCell(label_40);
			ws.addCell(label_50);
			ws.addCell(label_60);
			ws.addCell(label_70);
			ws.addCell(label_80);
			ws.addCell(label_90);
			ws.addCell(label_100);
			ws.addCell(label_110);
			ws.addCell(label_120);
			ws.addCell(label_130);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			// 写用户数据到EXCEL
			for (int i = 0, len = utList.size(); i < len; i++) {
				UserTogether ut = utList.get(i);

				User user = ut.getUser();
				Academy academy = ut.getAcademy();
				Department department = ut.getDepartment();
				Job job = ut.getJob();

				String uid = user.getUid();
				String username = user.getUsername();
				String departmentname = department.getDepartmentname();
				String jobname = job.getJobname();
				String email = user.getEmail();
				String cornet = user.getCornet();
				String telephone = user.getTelephone();
				String location = user.getLocation();
				String academyname = academy.getAcademyname();
				String major = user.getMajor();
				String dormitory = user.getDormitory();
				String islock = user.getIslock() == 1 ? "锁定" : "可用";
				String addtime = sdf.format(user.getAddtime());
				String modifytime = sdf.format(user.getModifytime());

				Label label_uid = new Label(0, i + 1, uid);
				Label label_username = new Label(1, i + 1, username);
				Label label_departmentname = new Label(2, i + 1, departmentname);
				Label label_jobname = new Label(3, i + 1, jobname);
				Label label_email = new Label(4, i + 1, email);
				Label label_cornet = new Label(5, i + 1, cornet);
				Label label_telephone = new Label(6, i + 1, telephone);
				Label label_location = new Label(7, i + 1, location);
				Label label_academyname = new Label(8, i + 1, academyname);
				Label label_major = new Label(9, i + 1, major);
				Label label_dormitory = new Label(10, i + 1, dormitory);
				Label label_islock = new Label(11, i + 1, islock);
				Label label_addtime = new Label(12, i + 1, addtime);
				Label label_modifytime = new Label(13, i + 1, modifytime);

				ws.addCell(label_uid);
				ws.addCell(label_username);
				ws.addCell(label_departmentname);
				ws.addCell(label_jobname);
				ws.addCell(label_email);
				ws.addCell(label_cornet);
				ws.addCell(label_telephone);
				ws.addCell(label_location);
				ws.addCell(label_academyname);
				ws.addCell(label_major);
				ws.addCell(label_dormitory);
				ws.addCell(label_islock);
				ws.addCell(label_addtime);
				ws.addCell(label_modifytime);

			}

			wwb.write();
			wwb.close();

			// 强制输出
			sos.flush();
			sos.close();
		} catch (FileNotFoundException e) {
			log.error(e, e.getCause());
		} catch (IOException e) {
			log.error(e, e.getCause());
		} catch (RowsExceededException e) {
			log.error(e, e.getCause());
		} catch (WriteException e) {
			log.error(e, e.getCause());
		}

	}
}
