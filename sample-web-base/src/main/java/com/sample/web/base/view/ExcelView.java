package com.sample.web.base.view;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.sample.common.util.EncodeUtils;

import lombok.val;

/**
 * Excelビュー
 */
public class ExcelView extends AbstractXlsxView {

    protected String filename;

    protected Callback callback;

    /**
     * コンストラクタ
     */
    public ExcelView() {
        setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=Windows-31J;");
    }

    /**
     * コンストラクタ
     * 
     * @param filename
     */
    public ExcelView(String filename, Callback callback) {
        this();
        this.filename = filename;
        this.callback = callback;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // ファイル名に日本語を含めても文字化けしないようにUTF-8にエンコードする
        val encodedFilename = EncodeUtils.encodeUtf8(filename);
        val contentDisposition = String.format("attachment; filename*=UTF-8''%s", encodedFilename);
        response.setHeader(CONTENT_DISPOSITION, contentDisposition);

        // Excelブックを構築する
        callback.buildExcelWorkbook(model, workbook);
    }

    public interface Callback {

        /**
         * Excelブックを構築します。
         *
         * @param model
         * @param workbook
         */
        void buildExcelWorkbook(Map<String, Object> model, Workbook workbook);
    }
}
