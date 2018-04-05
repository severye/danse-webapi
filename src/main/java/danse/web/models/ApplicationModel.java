package danse.web.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import danse.entities.Box;
import danse.entities.Category;
import danse.entities.Colour;
import danse.entities.Kind;
import danse.entities.Product;
import danse.entities.Size;
import danse.entities.SizeQuantity;
import danse.entities.Type;
import danse.metier.IMetier;
import danse.web.helpers.Static;
@Component
public class ApplicationModel implements IMetier {
	
	@Autowired
	private IMetier metier;
	
	private List<Kind> kinds;
	private List<Colour> colours;
	private List<Type> types;
	private List<Size> sizes;
	private List<Category> categories;
	private List<Box> boxes;
	private List<SizeQuantity> sizeQuantities;
	private List<Product> products;
	private List<String> messages;
	
	// données de configuration
	private boolean CORSneeded = true;
	
	@PostConstruct
	public void init() {
		// on récupère les médecins et les clients
		try {
			kinds = metier.getAllKinds();
			colours = metier.getAllColours();
			types = metier.getAllTypes();
			sizes = metier.getAllSizes();
			categories = metier.getAllCategories();
			boxes = metier.getAllBoxes();
			products = metier.getAllProducts();
			sizeQuantities = metier.getAllSizeQuantities();
		} catch (Exception ex) {
			messages = Static.getErreursForException(ex);
		}
	}

	// getter
	public List<String> getMessages() {
		return messages;
	}

	@Override
	public List<Type> getAllTypes() {
		return types;
	}

	@Override
	public List<Colour> getAllColours() {
		return colours;
	}

	@Override
	public List<Kind> getAllKinds() {
		return kinds;
	}

	@Override
	public List<Product> getAllProducts() {
		return products;
	}

	@Override
	public List<Box> getAllBoxes() {
		return boxes;
	}

	@Override
	public List<Size> getAllSizes() {
		return sizes;
	}

	@Override
	public List<SizeQuantity> getAllSizeQuantities() {
		return sizeQuantities;
	}

	@Override
	public List<Category> getAllCategories() {
		return categories;
	}

	@Override
	public Product addProduct(String name, String comment, String picture, Integer totalQuantity,
			Colour colour, Kind kind, Category category, Box box, Type type,List<SizeQuantity> sizeQuantities) {
		Product product = metier.addProduct(name, comment, picture, totalQuantity, colour, kind, category, box, type,sizeQuantities);
		products = metier.getAllProducts();
		return product;
	}

	@Override
	public Product getProduct(long idProduct) {
		return metier.getProduct(idProduct);
	}

	@Override
	public Size getSize(Long idSize) {
		return metier.getSize(idSize);
	}

	@Override
	public Type getType(Long idType) {
		return metier.getType(idType);
	}

	@Override
	public Kind getKind(Long idKind) {
		return metier.getKind(idKind);
	}

	@Override
	public Colour getColour(Long idColour) {
		return metier.getColour(idColour);
	}

	@Override
	public Category getCategory(Long idCategory) {
		return metier.getCategory(idCategory);
	}

	@Override
	public Box getBox(Long idBox) {
		return metier.getBox(idBox);
	}

	public boolean isCORSneeded() {
		return CORSneeded;
	}

	@Override
	public void deleteType(Long id) {
		metier.deleteType(id);
		types = metier.getAllTypes();
		
	}

	@Override
	public void deleteKind(Long id) {
		metier.deleteKind(id);
		kinds = metier.getAllKinds();
		
	}

	@Override
	public void deleteColour(Long id) {
		metier.deleteColour(id);
		colours = metier.getAllColours();
		
	}

	@Override
	public void deleteProduct(Long id) {
		metier.deleteProduct(id);
		products = metier.getAllProducts();
	}

	@Override
	public void deleteBox(Long id) {
		metier.deleteBox(id);
		boxes = metier.getAllBoxes();
	}

	@Override
	public void deleteSize(Long id) {
		metier.deleteSize(id);
		sizes = metier.getAllSizes();
	}

	@Override
	public void deleteSizeQuantity(Long idProduct, Long idSize) {
		metier.deleteSizeQuantity(idProduct, idSize);
		sizeQuantities = metier.getAllSizeQuantities();
	}

	@Override
	public void deleteCategory(Long id) {
		metier.deleteCategory(id);
		categories = metier.getAllCategories();
	}

	@Override
	public Type addOrUpdateType(Type type) {
		Type result = metier.addOrUpdateType(type);
		types = metier.getAllTypes();
		return result;
	}

	@Override
	public Kind addOrUpdateKind(Kind kind) {
		Kind result = metier.addOrUpdateKind(kind);
		kinds = metier.getAllKinds();
		return result;
	}

	@Override
	public Colour addOrUpdateColour(Colour colour) {
		Colour result = metier.addOrUpdateColour(colour);
		colours = metier.getAllColours();
		return result;
	}

	@Override
	public Product addOrUpdateProduct(Product product) {
		Product result = metier.addOrUpdateProduct(product);
		products = metier.getAllProducts();
		return result;
	}

	@Override
	public Box addOrUpdateBox(Box box) {
		Box result = metier.addOrUpdateBox(box);
		boxes = metier.getAllBoxes();
		return result;
	}

	@Override
	public Size addOrUpdateSize(Size size) {
		Size result = metier.addOrUpdateSize(size);
		sizes = metier.getAllSizes();
		return result;
	}

	@Override
	public SizeQuantity addOrUpdateSizeQuantity(SizeQuantity sizeQuantity) {
		return metier.addOrUpdateSizeQuantity(sizeQuantity);
	}

	@Override
	public Category addOrUpdateCategory(Category category) {
		Category result = metier.addOrUpdateCategory(category);
		categories = metier.getAllCategories();
		return result;
	}

	@Override
	public SizeQuantity getSizeQuanity(Long idProduct, Long idSize) {
		return metier.getSizeQuanity(idProduct, idSize);
	}

}
