package pmd.sql.developer.menu;

import java.awt.Component;
import java.io.*;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.dialogs.ExceptionDialog;
import oracle.ide.editor.Editor;
import oracle.ide.view.View;

public class PmdToolController implements Controller {

	static final Logger logger = Logger.getLogger(PmdToolController.class.getName());

//	public static int PmdTool_TEST_CMD_ID = Ide.findCmdID("PmdTool.test").intValue();
	public static int PmdTool_COVERAGE_CMD_ID = Ide.findCmdID("PmdTool.coverage").intValue();
//	public static int PmdTool_GENERATE_CMD_ID = Ide.findCmdID("PmdTool.generate").intValue();
//	public static final IdeAction PmdTool_TEST_ACTION = IdeAction.get(PmdToolController.PmdTool_TEST_CMD_ID);
	public static final IdeAction PmdTool_COVERAGE_ACTION = IdeAction.get(PmdToolController.PmdTool_COVERAGE_CMD_ID);
//	public static final IdeAction PmdTool_GENERATE_ACTION = IdeAction.get(PmdToolController.PmdTool_GENERATE_CMD_ID);

	@Override
	public boolean handleEvent(IdeAction action, Context context) {
//		if (action.getCommandId() == PmdTool_TEST_CMD_ID) {
//			runTest(context);
//			return true;
//		} else
			if (action.getCommandId() == PmdTool_COVERAGE_CMD_ID) {
			codeCoverage(context);
			return true;
		}
//			else if (action.getCommandId() == PmdTool_GENERATE_CMD_ID) {
//			generateTest(context);
//			return true;
//		}
		return false;
	}

	@Override
	public boolean update(IdeAction action, Context context) {
		return false;
	}

	private void runTest(Context context) {
		ExceptionDialog.showExceptionDialog(context, new RuntimeException(), "AAAA11122222A");
	}

	void codeCoverage(Context context) {
		View view = context.getView();
		if (view instanceof Editor) {
			Component component = ((Editor) view).getDefaultFocusComponent();
			if (component instanceof JEditorPane) {

				String textFromEditor = ((JEditorPane) component).getText();
//				ExceptionDialog.showExceptionDialog(context, new RuntimeException(),
//						textFromEditor);

				String pmdInputFile = "..\\..\\PMD\\PmdInputStream.sql";

				try {
					FileWriter writer = new FileWriter(pmdInputFile,false);
					writer.write(textFromEditor);
					writer.close();
				} catch (IOException e) {
					ExceptionDialog.showExceptionDialog(context, e);
					e.printStackTrace();
				}

			}
		}

		try {

			Process proc = Runtime.getRuntime()
					.exec(new String[] { "..\\..\\PMD\\startPmdCheck.bat" });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ExceptionDialog.showExceptionDialog(context, e);
			e.printStackTrace();
		}
	}

	void generateTest(Context context) {
		ExceptionDialog.showExceptionDialog(context, new RuntimeException(), " generateTest ");
	}

}
