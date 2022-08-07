package jjfactory.selecttuning.service.orders;

import jjfactory.selecttuning.domain.orders.Item;
import jjfactory.selecttuning.dto.req.ItemCreate;
import jjfactory.selecttuning.dto.res.ItemRes;
import jjfactory.selecttuning.repository.orders.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemRes findItem(Long id){
        Item item = getItem(id);
        return new ItemRes(item);
    }

    public List<ItemRes> findItems(){
        List<Item> items = itemRepository.findAll();
        return items.stream().map(ItemRes::new)
                .collect(Collectors.toList());
    }

    public String create(ItemCreate dto){
        Item item = Item.create(dto);
        itemRepository.save(item);
        return "y";
    }

    public String delete(Long itemId){
        Item item = getItem(itemId);
        item.delete();
        return "y";
    }

    private Item getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(NoSuchElementException::new);
        return item;
    }
}
