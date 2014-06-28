package fr.gdfsuez.hack1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import fr.gdfsuez.hack1.view.CoupledCameraSizes;
import android.app.Activity;
import android.app.Application;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.WindowManager;

/**
 * Application
 * 
 * @author remi
 * 
 */
public class GDFSuezApplication extends Application {

	// Instance de l'application
	static private GDFSuezApplication APP_INSTANCE = null;

	// Instance unique de la Camera
	static private Camera camera;

	private List<ElectricObject> objects = null;

	private int realtimeconsumption;
	private int estimatedconsumption;
	private int averageconsumption;

	/**
	 * Constructeur
	 */
	public GDFSuezApplication() {
		super();
		APP_INSTANCE = this;
		generateDatas();
	}

	static final public GDFSuezApplication getAppInstance() {
		return APP_INSTANCE;
	}

	public int getRealTimeConsumption() {
		return realtimeconsumption;
	}

	public List<ElectricObject> getObjects() {
		return objects;
	}

	public int getEstimatedConsumption() {
		return estimatedconsumption;
	}

	public int getAverageConsumption() {
		return averageconsumption;
	}

	private void generateDatas() {
		Random random = new Random();
		objects = new ArrayList<ElectricObject>();

		averageconsumption = Math.abs((random.nextInt() % 400) + 300);
		realtimeconsumption = Math.abs((random.nextInt() % 700) + 100);
		estimatedconsumption = (int) ((float) realtimeconsumption * 1.1F);

		boolean b1 = random.nextBoolean();
		objects.add(new ElectricObject("Ordinateur", "Acer Aspire Z3", 500,
				(b1) ? random.nextInt() % 500 : 0, b1));

		boolean b2 = random.nextBoolean();
		objects.add(new ElectricObject("Lampe", "Ikea BASISK Rail plafond", 60, (b2) ? 60 : 0, b2));

		boolean b3 = random.nextBoolean();
		objects.add(new ElectricObject("Lampadaire", "Ikea ALÄNG", 90, (b3) ? 90 : 0, b3));

		boolean b4 = random.nextBoolean();
		objects.add(new ElectricObject("Téléviseur", "Sony Bravia X9500B", 200, (b4) ? 200 : 8, b4 ? 1 : 2));

		boolean b5 = random.nextBoolean();
		objects.add(new ElectricObject("Lave-Linge", "Whirlpool AWG712D", 800, (b5) ? 800 : 0, b5));

		boolean b6 = random.nextBoolean();
		objects.add(new ElectricObject("Lampe", "Ikea HEKTAR Suspension", 120, (b6) ? 120 : 5, b6 ? 1 : 2));

	}

	/* *** METHODES STATIQUES D'UTILISATION DE CAMERA *** */

	/**
	 * Capture de camera
	 */
	static final public void captureCamera(Camera.PictureCallback picturecallback) {
		synchronized (APP_INSTANCE) {
			try {
				// Capture de caméra
				camera.takePicture(null, null, picturecallback);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Démarrage de la camera
	 */
	static final public void startCamera(SurfaceHolder holder, Camera.PreviewCallback previewcallback) {
		synchronized (APP_INSTANCE) {
			try {
				// On initialise la preview
				camera.setPreviewDisplay(holder);
				camera.startPreview();
				camera.setPreviewCallback(previewcallback);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}

	/**
	 * Initialisation de la camera
	 * 
	 * @param cameraId
	 *            Id de la camera à initialiser
	 * @param width
	 * @param height
	 */
	static final public void initCamera(int cameraId, int width, int height) {
		synchronized (APP_INSTANCE) {
			try {
				// Dimensions de la view
				int longueur = width;
				int hauteur = height;
				// On vérifie le sens de la view
				if (width < height) {
					// On inverse pour être cohérent
					longueur = height;
					hauteur = width;
				}
				// On change le cameraid si besoin
				// this.cameraid = cameraId;
				// On allume la camera
				camera = Camera.open(cameraId);
				// this.setWillNotDraw(false);
				// Paramètres
				Camera.Parameters params = camera.getParameters();
				// On récupère les définitions de capteurs
				List<Size> picturesizes = params.getSupportedPictureSizes();
				List<Size> previewsizes = params.getSupportedPreviewSizes();
				// Map de classements de ratio de capteurs
				HashMap<Float, CoupledCameraSizes> ratiomap = new HashMap<Float, CoupledCameraSizes>();
				// Le ratio de la view
				float viewratio = (float) longueur / (float) hauteur;
				// Parcours de tailles de capteurs
				for (Size size : picturesizes) {
					// Calcul de ratio
					float ratio = (float) size.width / (float) size.height;
					// On récupère une éventuelle taille déjà existante
					CoupledCameraSizes sizes = ratiomap.get(Float.valueOf(ratio));
					// On vérifie l'instance
					if (sizes == null) {
						// On instancie
						sizes = new CoupledCameraSizes(ratio, size);
						// On insère dans la map des résultats
						ratiomap.put(ratio, sizes);
					} else if (size.width > longueur && size.height > hauteur
							&& size.width < sizes.getPictureSize().width
							&& size.height < sizes.getPictureSize().height) {
						// On vérifie si on a une taille suffisante et si elle est plus petite que celle
						// existante (inutile d'avoir une trop grande taille)
						sizes.setPictureSize(size);
					}
					// On vérifie si on a déjà une preview
					if (sizes.getPreviewSize() == null) {
						// Parcourt des previews
						for (Size previewsize : previewsizes) {
							// Calcul de ratio
							float previewratio = (float) previewsize.width / (float) previewsize.height;
							// On vérifie si on a le même ratio
							if (previewratio == ratio) {
								// On vérifie si on en a déjà un ou s'il convient mieux
								if (previewsize.width <= sizes.getPictureSize().width
										&& previewsize.height <= sizes.getPictureSize().height
										&& (sizes.getPreviewSize() == null || (previewsize.width > sizes
												.getPreviewSize().width && previewsize.height > sizes
												.getPreviewSize().height))) {
									// On a trouvé le preview on le défini
									sizes.setPreviewSize(previewsize);
								}
							}
						}
					}
				} // Parcours de tailles de capteurs
					// On récupère l'orientation
				Display display = ((WindowManager) APP_INSTANCE.getSystemService(Activity.WINDOW_SERVICE))
						.getDefaultDisplay();
				// On vérifie et on applique la rotation
				if (display.getRotation() == Surface.ROTATION_0) {
					// On applique la rotation
					camera.setDisplayOrientation(90);
				} else if (display.getRotation() == Surface.ROTATION_90) {
					// On applique la rotation
					camera.setDisplayOrientation(0);
				} else if (display.getRotation() == Surface.ROTATION_180) {
					// On applique la rotation
					camera.setDisplayOrientation(270);
				} else if (display.getRotation() == Surface.ROTATION_270) {
					// On applique la rotation
					camera.setDisplayOrientation(180);
				}
				// Le couple picture / preview qui sera choisi
				CoupledCameraSizes choosen = null;
				// Le plus proche ratio de son affichage
				float closestratio = 15666;
				// On parcourt les
				for (CoupledCameraSizes couplesizes : ratiomap.values()) {
					// Calcul de différence de ratio
					float ratio = Math.abs(couplesizes.getRatio() - viewratio);
					// On vérifie si les dimensions du capteur ne sont pas trop petit
					if (couplesizes.getPictureSize().width < longueur) {
						// Ajout d'un coeff de pénalité
						ratio += (float) longueur / (float) couplesizes.getPictureSize().width;
					}
					// On vérifie l'instace et si le ratio est plus proche
					if (couplesizes.getPreviewSize() != null && ratio < closestratio) {
						// On a trouvé les tailles
						closestratio = ratio;
						choosen = couplesizes;
					}
					// TODO
					System.out.print(" " + closestratio + " =? " + ratio + "  ");
					System.out.print("  -> " + couplesizes.getRatio() + "  picture : "
							+ couplesizes.getPictureSize().width + "x" + couplesizes.getPictureSize().height);
					if (couplesizes.getPreviewSize() != null) {
						System.out.println(" preview : " + couplesizes.getPreviewSize().width + "x"
								+ couplesizes.getPreviewSize().height);
					} else {
						System.out.println(" preview : null");
					}
				}
				// On vérifie si on a trouvé les formats
				if (choosen != null) {
					// On défini les formats trouvés
					params.setPictureSize(choosen.getPictureSize().width, choosen.getPictureSize().height);
					params.setPreviewSize(choosen.getPreviewSize().width, choosen.getPreviewSize().height);
				}
				// On réatribue les paramètres pour les réinitialiser (obligatoire sur certains smartphones)
				camera.setParameters(params);

				// TODO
				Size s1 = camera.getParameters().getPictureSize();
				Size s2 = camera.getParameters().getPreviewSize();
				System.out.println(" picture : " + s1.width + " " + s1.height);
				System.out.println(" preview : " + s2.width + " " + s2.height);

			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Fin d'utilisation de la caméra
	 */
	static final public void stopCamera() {
		synchronized (APP_INSTANCE) {
			try {
				// Stop de la preview et libération des resources
				camera.stopPreview();
				camera.setPreviewCallback(null);
				camera.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
