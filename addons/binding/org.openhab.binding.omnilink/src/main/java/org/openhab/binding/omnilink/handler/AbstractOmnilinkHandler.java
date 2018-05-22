package org.openhab.binding.omnilink.handler;

import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusInfo;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.openhab.binding.omnilink.OmnilinkBindingConstants;

public abstract class AbstractOmnilinkHandler extends BaseThingHandler {

    public AbstractOmnilinkHandler(Thing thing) {
        super(thing);
    }

    public OmnilinkBridgeHandler getOmnilinkBridgeHander() {
        return (OmnilinkBridgeHandler) getBridge().getHandler();
    }

    /**
     * Returns the bridge of the thing.
     *
     * @return returns the bridge of the thing or null if the thing has no
     *         bridge
     */
    @Override
    protected Bridge getBridge() {
        ThingUID bridgeUID = thing.getBridgeUID();
        synchronized (this) {
            if (bridgeUID != null && thingRegistry != null) {
                return (Bridge) thingRegistry.get(bridgeUID);
            } else {
                return null;
            }
        }
    }

    /**
     * Gets the configured number for a thing.
     *
     * @return Configured number for a thing.
     */
    protected int getThingNumber() {
        return ((Number) getThing().getConfiguration().get(OmnilinkBindingConstants.THING_PROPERTIES_NUMBER))
                .intValue();
    }

    @Override
    public void bridgeStatusChanged(ThingStatusInfo bridgeStatusInfo) {
        super.bridgeStatusChanged(bridgeStatusInfo);
        if (bridgeStatusInfo.getStatus() == ThingStatus.ONLINE) {
            updateStatus(ThingStatus.ONLINE);
        }
    }

}