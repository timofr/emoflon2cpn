/**
 * 
 */
package simulation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import translation.DirectoryHandler;
import translation.Translation;

/**
 * @author Timo Freitag
 *
 */
public class XmiReader {
	URLClassLoader classLoader;
	ResourceSet resourceSet;
	Resource resource;
	DirectoryHandler directoryHandler = Translation.getTranslation().getDirectoryHandler();
	
	public XmiReader(URLClassLoader classLoader) {
		this.classLoader = classLoader;
	}
	
	public void initialize(String projectName) {
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(ResourceFactoryRegistryImpl.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		try {
			Class<?> pack = classLoader.loadClass(directoryHandler.getFullClassName(projectName + "Package"));
			Field field = pack.getField("eINSTANCE");
			field.get(null);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | ClassNotFoundException e) {
			throw new SimulationException("Failed to initialize the XmiReader");
		}
	}
	
	public Object load(File file) {
		resource = resourceSet.createResource(URI.createFileURI(file.getAbsolutePath()));
		try {
			resource.load(null);
			return resource.getContents().get(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save() {
		try {
			resource.save(null);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	private ResourceSet resSet;
	private Object object;
	private Resource resource;

	public void initialize() {
		
		
		
		resSet = new ResourceSetImpl();
		resSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(ResourceFactoryRegistryImpl.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
	}

	public void loadXMI(String path) {
		WSN_LanguagePackage.eINSTANCE.getClass();

		File file = new File(path);
		//
		// if (!file.exists()) {
		// file = new File("instances/WSNSample.xmi");
		// }
		//

		resource = resSet.createResource(URI.createFileURI(file.getAbsolutePath()));

		try {
			resource.load(null);
			EObject loadedObject = resource.getContents().get(0);
			wsn = (WirelessSensorNetwork) loadedObject;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WirelessSensorNetwork getWsn() {
		return wsn;
	}

	public void save() {
		try {
			resource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	/*
		private LeitnersBoxView view;

	private ResourceSet resSet;

   private Box box;

   Resource resource;

   public LeitnersBoxController()
   {
		resSet = new ResourceSetImpl();
		resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(ResourceFactoryRegistryImpl.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
				
		// initialize the LearningBoxLanguage
		LearningBoxLanguagePackage.eINSTANCE.getClass();
	}

	public void setView(LeitnersBoxView view) {
		this.view = view;
	}

   private String partitionToString(Partition partition)
   {
      if (partition == null)
         return "";
      return "Partition " + partition.getIndex();
   }

   public void loadXmiFile()
   {
      // TODO: Change this statement if you did not persist your instance file in 'instances'
      File file = new File("instances/Box.xmi");
	  if(!file.exists()){
		  file = new File("instances/BoxSample.xmi");
	  }
      resource = resSet.createResource(URI.createFileURI(file.getAbsolutePath()));

      try
      {
         resource.load(null);
         EObject loadedObject = resource.getContents().get(0);
         box = (Box) loadedObject;

         // Read instance file and generate all partitions + cards
         createPartitions(box);
				
      } catch (IOException e)
      {
				e.printStackTrace();
			}
	}

   public void createPartitions(Box box)
   {
      int partitionIndex = 0;
      for (Partition part : box.getContainedPartition())
      {
         view.createPartition(partitionToString(part));
         createCardsForPartition(part, partitionIndex++);
      }
   }

   private void createCardsForPartition(Partition partition, int partIndex)
   {
      int cardIndex = 0;
      for (Card card : partition.getCard())
      {
         view.createCard(partitionToString(partition), card.getBack(), partIndex, cardIndex++);
		}
	}

   public void doActionRemoveCard(int partIndex, int cardIndex)
   {
      // 'Grab' cards selected in the JFrame
      Partition containerPartion = box.getContainedPartition().get(partIndex);
      Card toBeRemoved = box.getContainedPartition().get(partIndex).getCard().get(cardIndex);

      // Part II; User function implemented with injections
      containerPartion.removeCard(toBeRemoved);

      // Save box.xmi instance and refresh GUI
      view.refreshGUI();
   }

   public void doActionCheckCard(int partIndex, int cardIndex)
   {
      // Grab specific card selected in JFrame
      Partition containerPartition = box.getContainedPartition().get(partIndex);
      Card cardToBeChecked = box.getContainedPartition().get(partIndex).getCard().get(cardIndex);

      // Prompt user for guess against card
      String guess = view.getUserGuess();
      System.out.println("Your guess: " + guess);

      // TODO: Part III; User function implemented with SDMs. Uncomment the statement below to activate
      containerPartition.check(cardToBeChecked, guess);

      // Save box.xmi instance and refresh GUI
      view.refreshGUI();
   }
	 */