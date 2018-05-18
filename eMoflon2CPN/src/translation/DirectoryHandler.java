/**
 * 
 */
package translation;

import java.io.File;
import java.util.Arrays;
import java.util.List;


/**
 * @author Timo Freitag
 *
 */
public class DirectoryHandler {
	private File project;
	private File ecore;
	private File[] xmi;
	private File bin;
	private File cpn;
	
	public DirectoryHandler(File project) {
		this.project = project;
	}
	
	public void initialize() {
		ecore = new File(project.getAbsolutePath() + File.separatorChar + "model" + File.separatorChar + project.getName() + ".ecore");
		bin = new File(project.getAbsolutePath() + File.separatorChar + "bin" + File.separator);
		xmi = getDynamicObjectFiles(new File(project.getAbsolutePath() + File.separatorChar + "instances"));
		cpn = new File(project.getAbsolutePath() + File.separator + "cpn" + File.separator + project.getName() +".cpn");
	}
	
	private File[] getDynamicObjectFiles(File instances) {
		return instances.listFiles(f ->  f.getName().toLowerCase().endsWith(".xmi"));
	}

	public File getProject() {
		return project;
	}

	public File getEcore() {
		return ecore;
	}

	public File[] getXmi() {
		return xmi;
	}
	
	public File getCpn() {
		return cpn;
	}
	
	

	public File getBin() {
		return bin;
	}
	
	public String getFullClassName(String name) {
		if(name.endsWith("Impl"))
			return project.getName() + ".impl." + name;
		return project.getName() + "." + name;
	}
}
