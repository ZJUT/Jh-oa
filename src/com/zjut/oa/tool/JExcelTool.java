package com.zjut.oa.tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.mvc.domain.User;

public class JExcelTool {

	private static final Log log = LogFactory.getLog(JExcelTool.class);

	@SuppressWarnings("deprecation")
	public static void exportUserToOutputStream(String savefilename,
			List<User> userList, HttpServletResponse resp) {
		WritableWorkbook wwb = null;
		try {
			resp.setHeader("Content-disposition", "attachment; filename="
					+ URLEncoder.encode(savefilename));
			ServletOutputStream sos = resp.getOutputStream();
			wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);
			// 格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, true);
			WritableCellFormat wcfF = new WritableCellFormat(wf);

			Label label00 = new Label(0, 0, "label00", wcfF);
			ws.addCell(label00);

			wwb.write();
			wwb.close();
			
			//强制输出
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
