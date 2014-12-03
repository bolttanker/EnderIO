package crazypants.enderio.machine.capbank.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import crazypants.enderio.machine.capbank.TileCapBank;

public class PacketNetworkIdResponse extends PacketCapBank<PacketNetworkIdResponse, IMessage> {

  private int id;
  
  public PacketNetworkIdResponse() {
  }

  PacketNetworkIdResponse(TileCapBank capBank) {
    super(capBank);
    if(capBank != null && capBank.getNetwork() != null) {
      id = capBank.getNetwork().getId();
    } else {
      id = -1;
    }
  }

  @Override
  public void toBytes(ByteBuf buf) {
    super.toBytes(buf);
    buf.writeInt(id);
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    super.fromBytes(buf);
    id = buf.readInt();
  }

  @Override
  protected IMessage handleMessage(TileCapBank te, PacketNetworkIdResponse message, MessageContext ctx) {
    te.setNetworkId(message.id);
    return null;
  }

}