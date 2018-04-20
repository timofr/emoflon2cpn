/**
 * 
 */
package simulation;

/**
 * @author Timo Freitag
 *
 */
public class Simulation<T> {
	/*
	private ResourceSet resSet;
	private T object;
	private Resource resource;

	public Simulation() {
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
}
