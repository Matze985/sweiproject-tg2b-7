package edu.hm.swe.eam;

import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * Created by Matthias Rude on 18.12.2017.
 * Email: Matze.development@gmail.com
 */
public abstract class BaseEqualsHashCodeTest<DataObject> {
    private final Supplier<DataObject> identObjSupplier;
    private final DataObject diffObj;

    public BaseEqualsHashCodeTest(Supplier<DataObject> identicalObjectSupplier, DataObject differentObject) {
        if (identicalObjectSupplier == null) {
            throw new NullPointerException("identical object supplier must not be null");
        }
        if (differentObject == null) {
            throw new NullPointerException("different object must not be null");
        }
        this.identObjSupplier = identicalObjectSupplier;
        this.diffObj = differentObject;
    }

    @Test
    public void identicalObjectsAreNotTheSameObject() {
        assertFalse(identObjSupplier.get() == identObjSupplier.get());
    }

    @Test
    public void equalsToReturnsTrueForIdenticalObjects() {
        assertEquals(identObjSupplier.get(), identObjSupplier.get());
    }

    @Test
    public void hashCodeReturnsSameHashCodeForIdenticalObjects() {
        assertEquals(identObjSupplier.get().hashCode(), identObjSupplier.get().hashCode());
    }

    @Test
    public void equalsToReturnsTrueForSameObject() {
        final DataObject first = identObjSupplier.get();
        assertEquals(first, first);
    }

    @Test
    public void equalsReturnsFalseForDifferentObjects() {
        if (diffObj != null) {
            assertNotEquals(identObjSupplier.get(), diffObj);
        }
    }

    @Test
    public void hashCodeReturnsDifferentHashCodeForDifferentObjects() {
        if (diffObj != null) {
            assertNotEquals(identObjSupplier.get().hashCode(), diffObj.hashCode());
        }
    }

    @Test
    public void equalsReturnsFalseForNullAsParameter() {
        assertNotEquals(identObjSupplier.get(), null);
    }

    @Test
    public void equalsReturnsFalseForWrongObject() {
        assertNotEquals(identObjSupplier.get(), new Object());
    }
}
