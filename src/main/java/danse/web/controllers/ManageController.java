package danse.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import danse.entities.Box;
import danse.entities.Category;
import danse.entities.Colour;
import danse.entities.Kind;
import danse.entities.Product;
import danse.entities.Size;
import danse.entities.SizeQuantity;
import danse.entities.SizeQuantityPk;
import danse.entities.Type;
import danse.web.helpers.Static;
import danse.web.models.ApplicationModel;
import danse.web.models.PostAddColour;
import danse.web.models.PostAddName;
import danse.web.models.PostAddProduct;
import danse.web.models.PostAddSizeQuantity;
import danse.web.models.PostDelete;
import danse.web.models.PostDeleteSizeQuantity;
import danse.web.models.Reponse;

@RestController
public class ManageController {

	@Autowired
	private ApplicationModel application;
	private List<String> messages;
	
	@Autowired
	private ManageCorsController manageCorsController;
	
	@PostConstruct
	public void init() {
		// messages d'erreur de l'application
		messages = application.getMessages();
	}
	@GetMapping("/")
	   String home(Principal user) {
	       return "Hello " + user.getName();
	   }
	
	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public Reponse getAllTypes(HttpServletResponse response) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		try {
			return new Reponse(0, application.getAllTypes());
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/kinds", method = RequestMethod.GET)
	public Reponse getAllKinds(HttpServletResponse response) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		try {
			return new Reponse(0, application.getAllKinds());
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/colours", method = RequestMethod.GET)
	public Reponse getAllColours(HttpServletResponse response) {
		manageCorsController.getAllColours(response);
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		try {
			return new Reponse(0, application.getAllColours());
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/sizes", method = RequestMethod.GET)
	public Reponse getAllSizes(HttpServletResponse response) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		try {
			return new Reponse(0, application.getAllSizes());
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/boxes", method = RequestMethod.GET)
	public Reponse getAllBoxes(HttpServletResponse response) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		try {
			return new Reponse(0, application.getAllBoxes());
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public Reponse getAllProducts(HttpServletResponse response) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		try {
			return new Reponse(0, application.getAllProducts());
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/sizeQuantities", method = RequestMethod.GET)
	public Reponse getAllSizeQuantities(HttpServletResponse response) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		try {
			return new Reponse(0, application.getAllSizeQuantities());
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public Reponse getAllCategories(HttpServletResponse response) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		try {
			return new Reponse(0, application.getAllCategories());
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	
	
	@RequestMapping(value = "/products", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addProduct(@RequestBody PostAddProduct post) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		String name = post.getName();
		String comment = post.getComment();
		String picture = post.getPicture();
		Integer totalQuantity = post.getTotalQuantity();
		Long idType = post.getIdType();
		Long idKind = post.getIdKind();
		Long idCategory = post.getIdCategory();
		Long idColour = post.getIdColour();
		Long idBox = post.getIdBox();
		List<PostAddSizeQuantity> postSizeQuantities = post.getSizeQuantities();
		List<SizeQuantity> sizeQuantities = new ArrayList<SizeQuantity>();
		SizeQuantity sizeQuantity;
		SizeQuantityPk sizeQuantityPk;
		Size size;
		Reponse reponse = null;
		if(postSizeQuantities!=null) {
			for (PostAddSizeQuantity postAddSizeQuantity : postSizeQuantities) {
				reponse = getSize(postAddSizeQuantity.getIdSize());
				size = (Size) reponse.getData();
				sizeQuantityPk = new SizeQuantityPk();
				sizeQuantityPk.setSize(size);
				sizeQuantity = new SizeQuantity(sizeQuantityPk, postAddSizeQuantity.getQuantity());
				sizeQuantities.add(sizeQuantity);
			}
		}
		Type type = null;
		if(idType!=null) {
			reponse=getType(idType);
			type = (Type) reponse.getData();
		}
		Kind kind = null;
		if(idKind!=null) {
			reponse=getKind(idKind);
			kind = (Kind) reponse.getData();
		}
		Category category = null;
		if(idCategory!=null) {
			reponse=getCategory(idCategory);
			category = (Category) reponse.getData();
		}
		Colour colour = null;
		if(idColour!=null) {
			reponse=getColour(idColour);
			colour = (Colour) reponse.getData();
		}
		Box box = null;
		if(idBox!=null) {
			reponse=getBox(idBox);
			box = (Box) reponse.getData();
		}
		try {
			return new Reponse(0, application.addProduct(name, comment, picture, totalQuantity, colour, kind, category, box, type,sizeQuantities));
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	public Reponse getProduct(Long idProduct) {
		Product product = null;
		try {
			product = application.getProduct(idProduct);
		}catch (Exception e1) {
			return new Reponse(1, Static.getErreursForException(e1));
		}
		if(product == null){
			return new Reponse(2, null);
		}
		return new Reponse(0, product);
	}
	
	public Reponse getSize(Long idSize) {
		Size size = null;
		try {
			size = application.getSize(idSize);
		}catch(Exception e1) {
			return new Reponse(1, Static.getErreursForException(e1));
		}
		if(size == null){
			return new Reponse(2, null);
		}
		return new Reponse(0, size);
	}
	
	public Reponse getKind(Long idKind) {
		Kind kind = null;
		try {
			kind = application.getKind(idKind);
		}catch(Exception e1) {
			return new Reponse(1, Static.getErreursForException(e1));
		}
		if(kind == null){
			return new Reponse(2, null);
		}
		return new Reponse(0, kind);
	}
	
	
	public Reponse getColour(Long idColour) {
		Colour colour = null;
		try {
			colour = application.getColour(idColour);
		}catch(Exception e1) {
			return new Reponse(1, Static.getErreursForException(e1));
		}
		if(colour == null){
			return new Reponse(2, null);
		}
		return new Reponse(0, colour);
	}
	
	public Reponse getCategory(Long idCategory) {
		Category category = null;
		try {
			category = application.getCategory(idCategory);
		}catch(Exception e1) {
			return new Reponse(1, Static.getErreursForException(e1));
		}
		if(category == null){
			return new Reponse(2, null);
		}
		return new Reponse(0, category);
	}

	public Reponse getBox(long idBox) {
		Box box = null;
		try {
			box = application.getBox(idBox);
		}catch (Exception e1) {
			return new Reponse(1, Static.getErreursForException(e1));
		}
		if(box == null){
			return new Reponse(2, null);
		}
		return new Reponse(0, box);
	}
	
	public Reponse getType(Long idType) {
		Type type = null;
		try {
			type = application.getType(idType);
		}catch (Exception e1) {
			return new Reponse(1, Static.getErreursForException(e1));
		}
		if(type == null){
			return new Reponse(2, null);
		}
		return new Reponse(0, type);
	}
	
	public Reponse getSizeQuantity(Long idProduct, Long idSize) {
		SizeQuantity sizeQuantity = null;
		try {
			sizeQuantity= application.getSizeQuanity(idProduct, idSize);
		}catch (Exception e1) {
			return new Reponse(1, Static.getErreursForException(e1));
		}
		if(sizeQuantity == null){
			return new Reponse(2, null);
		}
		return new Reponse(0, sizeQuantity);
	}
	

	@RequestMapping(value = "/deleteBox", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse deleteBox(@RequestBody PostDelete post) {
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		Reponse reponse = getBox(id);
		if (reponse.getStatus() != 0) {
			return reponse;
		}
		try {
			application.deleteBox(id);
		} catch (Exception e1) {
			return new Reponse(3, Static.getErreursForException(e1));
		}
		return new Reponse(0, null);
	}
	
	@RequestMapping(value = "/deleteKind", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse deleteKind(@RequestBody PostDelete post) {
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		Reponse reponse = getKind(id);
		if (reponse.getStatus() != 0) {
			return reponse;
		}
		try {
			application.deleteKind(id);
		} catch (Exception e1) {
			return new Reponse(3, Static.getErreursForException(e1));
		}
		return new Reponse(0, null);
	}
	
	@RequestMapping(value = "/deleteType", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse deleteType(@RequestBody PostDelete post) {
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		Reponse reponse = getType(id);
		if (reponse.getStatus() != 0) {
			return reponse;
		}
		try {
			application.deleteType(id);
		} catch (Exception e1) {
			return new Reponse(3, Static.getErreursForException(e1));
		}
		return new Reponse(0, null);
	}
	
	@RequestMapping(value = "/deleteColour", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse deleteColour(@RequestBody PostDelete post) {
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		System.out.println("id"+id);
		Reponse reponse = getColour(id);
		if (reponse.getStatus() != 0) {
			return reponse;
		}
		try {
			application.deleteColour(id);
		} catch (Exception e1) {
			return new Reponse(3, Static.getErreursForException(e1));
		}
		return new Reponse(0, null);
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse deleteCategory(@RequestBody PostDelete post) {
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		Reponse reponse = getCategory(id);
		if (reponse.getStatus() != 0) {
			return reponse;
		}
		try {
			application.deleteCategory(id);
		} catch (Exception e1) {
			return new Reponse(3, Static.getErreursForException(e1));
		}
		return new Reponse(0, null);
	}
	
	@RequestMapping(value = "/deleteSize", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse deleteSize(@RequestBody PostDelete post) {
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		Reponse reponse = getSize(id);
		if (reponse.getStatus() != 0) {
			return reponse;
		}
		try {
			application.deleteSize(id);
		} catch (Exception e1) {
			return new Reponse(3, Static.getErreursForException(e1));
		}
		return new Reponse(0, null);
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse deleteProduct(@RequestBody PostDelete post) {
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		Reponse reponse = getProduct(id);
		if (reponse.getStatus() != 0) {
			return reponse;
		}
		try {
			application.deleteProduct(id);
		} catch (Exception e1) {
			return new Reponse(3, Static.getErreursForException(e1));
		}
		return new Reponse(0, null);
	}
	
	@RequestMapping(value = "/deleteSizeQuantity", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse deleteSizeQuantity(@RequestBody PostDeleteSizeQuantity post) {
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long idProduct = post.getIdProduct();
		Long idSize = post.getIdSize();
		Reponse reponse = getSizeQuantity(idProduct, idSize);
		if (reponse.getStatus() != 0) {
			return reponse;
		}
		try {
			application.deleteSizeQuantity(idProduct, idSize);
		} catch (Exception e1) {
			return new Reponse(3, Static.getErreursForException(e1));
		}
		return new Reponse(0, null);
	}
	@RequestMapping(value = "/types", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse updateType(@RequestBody PostAddName post) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		String name = post.getName();
		Long id = post.getId();
		Type type = new Type();
		type.setId(id);
		type.setName(name);
		try {
			return new Reponse(0, application.addOrUpdateType(type));
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/kinds", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse updateKind(@RequestBody PostAddName post) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		String name = post.getName();
		Long id = post.getId();
		Kind kind= new Kind();
		kind.setId(id);
		kind.setName(name);
		
		try {
			return new Reponse(0, application.addOrUpdateKind(kind));
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/colours", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse updateColour(@RequestBody PostAddColour post) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		String name = post.getName();
		String codeColour = post.getColorCode();
		Colour colour = new Colour();
		colour.setColorCode(codeColour);
		colour.setId(id);
		colour.setName(name);
		try {
			return new Reponse(0, application.addOrUpdateColour(colour));
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/sizes", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse updateSize(@RequestBody PostAddName post) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		String name = post.getName();
		Size size = new Size();
		size.setId(id);
		size.setName(name);
		try {
			return new Reponse(0, application.addOrUpdateSize(size));
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse updateCategorie(@RequestBody PostAddName post) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		String name = post.getName();
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		try {
			return new Reponse(0, application.addOrUpdateCategory(category));
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/boxes", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse updateBox(@RequestBody PostAddName post) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		String name = post.getName();
		Box box = new Box();
		box.setId(id);
		box.setName(name);
		try {
			return new Reponse(0, application.addOrUpdateBox(box));
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/produc", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse updateProduct(@RequestBody PostAddProduct post) {
		// état de l'application
		if (messages != null) {
			return new Reponse(-1, messages);
		}
		Long id = post.getId();
		String name = post.getName();
		String comment = post.getComment();
		String picture = post.getPicture();
		Integer totalQuantity = post.getTotalQuantity();
		Long idType = post.getIdType();
		Long idKind = post.getIdKind();
		Long idCategory = post.getIdCategory();
		Long idColour = post.getIdColour();
		Long idBox = post.getIdBox();
		List<PostAddSizeQuantity> postSizeQuantities = post.getSizeQuantities();
		List<SizeQuantity> sizeQuantities = new ArrayList<SizeQuantity>();
		SizeQuantity sizeQuantity;
		SizeQuantityPk sizeQuantityPk;
		Size size;
		Reponse reponse = null;
		if(postSizeQuantities!=null) {
			for (PostAddSizeQuantity postAddSizeQuantity : postSizeQuantities) {
				reponse = getSize(postAddSizeQuantity.getIdSize());
				size = (Size) reponse.getData();
				sizeQuantityPk = new SizeQuantityPk();
				sizeQuantityPk.setSize(size);
				sizeQuantity = new SizeQuantity(sizeQuantityPk, postAddSizeQuantity.getQuantity());
				sizeQuantities.add(sizeQuantity);
			}
		}
		Type type = null;
		if(idType!=null) {
			reponse=getType(idType);
			type = (Type) reponse.getData();
		}
		Kind kind = null;
		if(idKind!=null) {
			reponse=getKind(idKind);
			kind = (Kind) reponse.getData();
		}
		Category category = null;
		if(idCategory!=null) {
			reponse=getCategory(idCategory);
			category = (Category) reponse.getData();
		}
		Colour colour = null;
		if(idColour!=null) {
			reponse=getColour(idColour);
			colour = (Colour) reponse.getData();
		}
		Box box = null;
		if(idBox!=null) {
			reponse=getBox(idBox);
			box = (Box) reponse.getData();
		}
		Product product = new Product(name, picture, comment, totalQuantity, category, type, colour, box, kind);
		product.setId(id);
		try {
			return new Reponse(0, application.addOrUpdateProduct(product));
		} catch (Exception e) {
			return new Reponse(1, Static.getErreursForException(e));
		}
	}
	
}
