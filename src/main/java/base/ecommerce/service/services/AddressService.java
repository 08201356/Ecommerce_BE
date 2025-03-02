package base.ecommerce.service.services;

import base.ecommerce.model.User;
import base.ecommerce.payload.entity_dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);
    List<AddressDTO> getAddresses();
    AddressDTO getAddressById(Long addressId);
    List<AddressDTO> getUserAddresses(User user);
    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);
    String deleteAddress(Long addressId);
}
