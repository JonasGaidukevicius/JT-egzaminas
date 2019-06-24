package lt.sventes.cart.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.sventes.cart.model.CartData;
import lt.sventes.cart.service.CartService;
import lt.sventes.holidays.service.HolidayService;

@RestController
@Api(value = "cart")
@RequestMapping(value = "/api/carts")
public class CartController {

	private final HolidayService holidayService;
	private final CartService cartService;

	// konstruktorius
	@Autowired
	public CartController(HolidayService holidayService, CartService cartService) {
		this.holidayService = holidayService;
		this.cartService = cartService;
	}

	@Autowired
	private ServletContext servletContext;

	// Visų krepšelių gavimas
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get carts list", notes = "Returns list of existing carts")
	public List<CartData> getFullListOfCartsHolidayList(
			String username) {
		return cartService.getFullListOfCarts(username);
	}

	// Naujo krepšelio sukūrimas
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new cart", notes = "Creates new cart with provided data")
	public ResponseEntity<?> createCart(
			@ApiParam(value = "Order data", required = true) @Valid @RequestBody final CreateCartCommand ccc) {

		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("Gautas vartotojas iš react vardas yra: " + ccc.getUsername());

		return cartService.createCart(ccc.getUsername(), ccc.getDescription());
	}
}
